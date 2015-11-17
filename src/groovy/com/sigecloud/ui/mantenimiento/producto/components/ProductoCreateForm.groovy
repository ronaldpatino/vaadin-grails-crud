package com.sigecloud.ui.mantenimiento.producto.components

import com.sigecloud.componetes.Sizer.Sizer
import com.sigecloud.mantenimiento.ProductoService
import com.sigecloud.modelo.Producto
import com.sigecloud.ui.mantenimiento.producto.views.ProductoListView
import com.sigecloud.util.ScNavigation
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.validator.StringLengthValidator
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.*

class ProductoCreateForm extends CustomComponent implements Button.ClickListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")


    TextField codigoProducto = new TextField("Codigo Producto")
    TextField codigoPorcentaje = new TextField("Codigo Porcentaje")
    TextField nombre = new TextField("Nombre")
    TextField porcentaje = new TextField("Porcentaje")
    TextField valor = new TextField("Valor")
    OptionGroup esPorcentajeValor = new OptionGroup("Producto es")



    Producto producto = new Producto()
    BeanFieldGroup<Producto> formFieldBindings;

    ProductoCreateForm() {

        /**
         * Depende de donde llames ponemos true o false
         */
        esPorcentajeValor.addItems(Boolean.TRUE , Boolean.FALSE )
        esPorcentajeValor.setItemCaption(Boolean.TRUE, "Porcentaje")
        esPorcentajeValor.setItemCaption(Boolean.FALSE, "Valor")
        esPorcentajeValor.setValue(Boolean.TRUE)

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

        //TipoProducto.values().each {tipo -> tipoProducto.addItem(tipo)}

        formLayout.addStyleName("light");
        formLayout.setMargin(true);
        formLayout.addComponent(nombre)
        formLayout.addComponent(codigoProducto)
        formLayout.addComponent(codigoPorcentaje)
        formLayout.addComponent(porcentaje)
        formLayout.addComponent(valor)
        formLayout.addComponent(esPorcentajeValor)


        //Valores por defecto para form
        nombre.setNullRepresentation("")
        codigoProducto.setNullRepresentation("")
        codigoPorcentaje.setNullRepresentation("")
        porcentaje.setNullRepresentation("0.0")
        valor.setNullRepresentation("0.0")

        /**
         * VALIDACIONES
         */

        nombre.addValidator(new StringLengthValidator("Debe ingresar un nombre", 3, 255, false))
        codigoProducto.addValidator(new StringLengthValidator("Debe el codigo del producto", 1, 255, false))
        codigoPorcentaje.addValidator(new StringLengthValidator("Debe el codigo del porcentaje", 1, 255, false))
        //porcentaje.addValidator(new StringLengthValidator("Debe ingresar un porcentaje", 1, 5, false))
        //valor.addValidator(new StringLengthValidator("Debe ingresar un valor", 1, 5, false))

        nombre.setImmediate(true);
        nombre.setImmediate(true);
        codigoProducto.setImmediate(true);
        codigoPorcentaje.setImmediate(true);
        porcentaje.setImmediate(true);
        valor.setImmediate(true);

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
                Notification.show("You fail!" + e.getMessage());
            }
        }
        else {
            formFieldBindings.discard()
            ScNavigation.navigateTo(ProductoListView.VIEW_NAME)
        }

    }
}
