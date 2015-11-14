package com.sigecloud.ui.factura.components


import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Label
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window

/**
 * Created by ronald on 09/11/15.
 */
class ItemAddForm  extends CustomComponent {

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")

    TextField codigo = new TextField("Codigo")
    TextField descripcion = new TextField("Descripcion")
    TextField cantidad = new TextField("Cantidad")
    TextField precioUnitario = new TextField("Precio unitario")
    TextField impuestos = new TextField("Impuestos")


    public ItemAddForm() {
        super(); // Set window caption

        // Some basic content for the window
        FormLayout content = new FormLayout();
        content.addComponent(codigo);
        content.addComponent(descripcion);
        content.addComponent(cantidad);
        content.addComponent(precioUnitario);
        content.addComponent(impuestos);


        content.setMargin(true);
        setCompositionRoot(content);

    }

}
