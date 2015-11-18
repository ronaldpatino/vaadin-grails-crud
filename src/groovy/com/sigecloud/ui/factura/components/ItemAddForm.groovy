package com.sigecloud.ui.factura.components

import com.sigecloud.componentes.Sizer.Sizer
import com.sigecloud.mantenimiento.ProductoService
import com.vaadin.annotations.Theme
import com.vaadin.data.Property
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.Button
import com.vaadin.ui.ComboBox
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.TextField

@Theme("sigecloud")
class ItemAddForm  extends CustomComponent  implements Button.ClickListener, Property.ValueChangeListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")

    TextField codigo = new TextField("Codigo")
    TextField descripcion = new TextField("Descripcion")
    TextField cantidad = new TextField("Cantidad")
    TextField precioUnitario = new TextField("Precio unitario")
    TextField impuestos = new TextField("Impuestos")
    ComboBox  productoComboBox = new ComboBox("Producto");

    public ItemAddForm() {
        super(); // Set window caption


        /**
         * Botones
         */
        HorizontalLayout botonesLayout = new HorizontalLayout(guardarButton, new Sizer("1em", null) ,cancelButton)

        guardarButton.setStyleName("primary")
        guardarButton.setIcon(FontAwesome.FLOPPY_O)
        cancelButton.setIcon(FontAwesome.TIMES_CIRCLE_O)

        guardarButton.addClickListener(this)
        cancelButton.addClickListener(this)
        productoComboBox.addValueChangeListener(this)


        // Some basic content for the window
        FormLayout content = new FormLayout();
        content.addStyleName("light");
        content.setMargin(true);

        def productos = Grails.get(ProductoService).getProductos()

        productos.each {
            producto -> productoComboBox.addItem(producto)
        }

        content.addComponent(productoComboBox)
        content.addComponent(codigo)
        content.addComponent(descripcion)
        content.addComponent(cantidad)
        content.addComponent(precioUnitario)
        content.addComponent(impuestos)
        content.addComponent(botonesLayout)


        content.setMargin(true);
        setCompositionRoot(content);

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {

    }

    @Override
    void valueChange(Property.ValueChangeEvent valueChangeEvent) {

    }
}
