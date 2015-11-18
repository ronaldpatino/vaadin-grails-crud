package com.sigecloud.ui.mantenimiento.producto.components

import com.sigecloud.componentes.Sizer.Sizer
import com.sigecloud.modelo.Impuesto
import com.sigecloud.modelo.UnidadMedida
import com.vaadin.server.FontAwesome
import com.vaadin.ui.Button
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window


class ImpuestoAddWindow extends Window implements  Button.ClickListener{

    ImpuestoAddForm impuestoAddForm = new ImpuestoAddForm()
    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")
    Impuesto impuesto

    ImpuestoAddWindow(){
        super("<h4>Agregar Impuesto a Producto</h4>")
        center()
        setCaptionAsHtml(true)
        setCaption("<h4>Agregar Impuesto a Producto</h4>")
        setModal(true);
        setClosable(false)
        setWidth("50%")

        VerticalLayout contentLayout = new VerticalLayout()
        HorizontalLayout botonesLayout = new HorizontalLayout(guardarButton, new Sizer("1em", null) ,cancelButton)

        botonesLayout.setMargin(true)
        guardarButton.setStyleName("primary")
        guardarButton.setIcon(FontAwesome.FLOPPY_O)
        cancelButton.setIcon(FontAwesome.TIMES_CIRCLE_O)

        guardarButton.addClickListener(this)
        cancelButton.addClickListener(this)

        contentLayout.addComponent(impuestoAddForm)
        contentLayout.addComponent(botonesLayout)

        setContent(contentLayout);

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {

        if(clickEvent.getSource() == cancelButton){
            UI.getCurrent().removeWindow(this)
        }

        if(clickEvent.getSource() == guardarButton){
            impuesto = impuestoAddForm.impuesto
            UI.getCurrent().removeWindow(this)
        }

    }
}
