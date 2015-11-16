package com.sigecloud.ui.mantenimiento.impuesto.components

import com.sigecloud.componetes.Sizer.Sizer
import com.sigecloud.mantenimiento.ImpuestoService
import com.sigecloud.modelo.Impuesto
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoListView
import com.sigecloud.util.ScNavigation
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.validator.StringLengthValidator
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.*

class ImpuestoEditForm extends CustomComponent implements Button.ClickListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")


    TextField codigoImpuesto = new TextField("Codigo Impuesto")
    TextField codigoPorcentaje = new TextField("Codigo Porcentaje")
    TextField nombre = new TextField("Nombre")
    TextField porcentaje = new TextField("Porcentaje")
    TextField valor = new TextField("Valor")

    CheckBox  esPorcentaje = new CheckBox("Es Porcentaje")
    CheckBox  esValor = new CheckBox("Es valor")


    Impuesto impuesto = new Impuesto()
    BeanFieldGroup<Impuesto> formFieldBindings;

    ImpuestoEditForm(Impuesto i) {

        impuesto = i
        /**
         * Depende de donde llames ponemos true o false
         */
        esPorcentaje.setValue(true)
        esValor.setValue(true)

        VerticalLayout verticalLayout = new VerticalLayout()

        /**
         * Botones
         */
        guardarButton.setStyleName("primary")
        guardarButton.setIcon(FontAwesome.FLOPPY_O)
        cancelButton.setIcon(FontAwesome.TIMES_CIRCLE_O)

        HorizontalLayout botonesLayout = new HorizontalLayout(guardarButton, new Sizer("1em", null), cancelButton)

        guardarButton.addClickListener(this)
        cancelButton.addClickListener(this)
        /**
         * Fin Botones
         */


        FormLayout formLayout = new FormLayout();

        //TipoImpuesto.values().each {tipo -> tipoImpuesto.addItem(tipo)}

        formLayout.addStyleName("light");
        formLayout.setMargin(true);
        formLayout.addComponent(nombre)
        formLayout.addComponent(codigoImpuesto)
        formLayout.addComponent(codigoPorcentaje)
        formLayout.addComponent(porcentaje)
        formLayout.addComponent(valor)
        formLayout.addComponent(esValor)
        formLayout.addComponent(esPorcentaje)


        //Valores por defecto para form
        nombre.setNullRepresentation("")


        /**
         * VALIDACIONES
         */

        nombre.addValidator(new StringLengthValidator("Debe ingresar un nombre", 3, 255, false))


        nombre.setImmediate(true);


        /**
         * FIN VALIDACIONES
         */

        /**
         * CONSTRAINTS
         */
        nombre.setMaxLength(255)

        /**
         * FIN CONSTRAINTS
         */

        /**
         * BINDING
         *
         * Debes enlazar los campos del objeto actual al bean para que funcione ojo
         * el this hace referecia a los campos de este objeto asi los relaciona
         */
        formFieldBindings = BeanFieldGroup.bindFieldsBuffered(impuesto, this)
        formFieldBindings.setItemDataSource(impuesto)

        verticalLayout.addComponent(botonesLayout)
        verticalLayout.addComponent(new Sizer(null,"1em"))
        Panel panel = new Panel()
        panel.setSizeFull()
        panel.setHeight("400px")
        panel.setContent(formLayout)

        verticalLayout.addComponent(panel)

        setCompositionRoot(verticalLayout);

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {
        if (clickEvent.getSource() == guardarButton){
            try{
                formFieldBindings.commit()
                Grails.get(ImpuestoService).save(impuesto)
                ScNavigation.navigateTo(ImpuestoListView.VIEW_NAME)
            }
            catch (FieldGroup.CommitException e) {
                Notification.show("You fail!");
            }
        }
        else {
            formFieldBindings.discard()
            ScNavigation.navigateTo(ImpuestoListView.VIEW_NAME)
        }

    }
}
