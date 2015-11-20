package com.sigecloud.ui.mantenimiento.producto.components

import com.sigecloud.componentes.Sizer.Sizer
import com.vaadin.server.FontAwesome
import com.vaadin.ui.Button
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window


class ImpuestoAddOGWindow extends Window implements  Button.ClickListener{

    ImpuestoAddOGForm impuestoAddOGForm = new ImpuestoAddOGForm()
    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")
    ProductoCreateOGForm.RespuestaModal respuestaModal

    ImpuestoAddOGWindow(ProductoCreateOGForm.RespuestaModal r){
        super("<h4>Agregar Impuesto a Producto</h4>")
        respuestaModal = r;
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


        contentLayout.addComponent(impuestoAddOGForm)
        contentLayout.addComponent(botonesLayout)

        setContent(contentLayout)

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {

        if(clickEvent.getSource() == cancelButton){
            UI.getCurrent().removeWindow(this)
        }

        if(clickEvent.getSource() == guardarButton){
            //accedo al valo del form y lo envio como espuesta asyn hacia el parent
            respuestaModal.impuesto(impuestoAddOGForm.impuestoGrid.impuestoGrid.getSelectedRows())
            UI.getCurrent().removeWindow(this)
        }

    }

}
