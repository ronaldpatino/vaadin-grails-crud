package com.sigecloud.ui.mantenimiento.producto.components

import com.sigecloud.componentes.Sizer.Sizer
import com.sigecloud.mantenimiento.ProductoService
import com.sigecloud.mantenimiento.UnidadMedidaService
import com.sigecloud.modelo.Producto
import com.sigecloud.ui.mantenimiento.producto.views.ProductoListView
import com.sigecloud.util.ScNavigation
import com.vaadin.data.Property
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.validator.StringLengthValidator
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.*

class ProductoEditForm extends CustomComponent implements Button.ClickListener, Property.ValueChangeListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")

    TextField nombre = new TextField("Nombre")
    ComboBox unidadMedidaComboBox = new ComboBox("Unidad Medida");

    Producto producto = new Producto()
    BeanFieldGroup<Producto> formFieldBindings;


    ProductoEditForm(Producto i) {

        producto = i
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
        unidadMedidaComboBox.addValueChangeListener(this)
        /**
         * Fin Botones
         */


        FormLayout formLayout = new FormLayout();

        /**
         * Cargamos el combobox
         */

        //Sacamos de la DB los items
        def unidadesMedida = Grails.get(UnidadMedidaService).getUnidadMedidas()

        //Cargamos en el componente
        unidadesMedida.each {
            um -> unidadMedidaComboBox.addItem(um.id)
                unidadMedidaComboBox.setItemCaption(um.id, um.toString())
        }

        //Seteamos el item selecccionado
        unidadMedidaComboBox.setValue(i.unidadMedida.id)


        formLayout.addStyleName("light");
        formLayout.setMargin(true);
        formLayout.addComponent(nombre)
        formLayout.addComponent(unidadMedidaComboBox)

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
        formFieldBindings = BeanFieldGroup.bindFieldsBuffered(producto, this)

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
                Grails.get(ProductoService).save(producto)
                ScNavigation.navigateTo(ProductoListView.VIEW_NAME)
            }
            catch (FieldGroup.CommitException e) {
                Notification.show("You fail!");
            }
        }
        else {
            formFieldBindings.discard()
            ScNavigation.navigateTo(ProductoListView.VIEW_NAME)
        }
    }

    @Override
    void valueChange(Property.ValueChangeEvent valueChangeEvent) {

        if(valueChangeEvent.getProperty() == unidadMedidaComboBox){
            if(valueChangeEvent.getProperty().getValue() != null){
                producto.unidadMedida = Grails.get(UnidadMedidaService).find(valueChangeEvent.getProperty().getValue())
            }
        }
    }

}
