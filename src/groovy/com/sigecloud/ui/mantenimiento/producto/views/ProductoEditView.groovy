package com.sigecloud.ui.mantenimiento.producto.views

import com.sigecloud.mantenimiento.ProductoService
import com.sigecloud.modelo.Producto
import com.sigecloud.ui.mantenimiento.producto.components.ProductoEditForm
import com.vaadin.grails.Grails
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout


class ProductoEditView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "editarProducto";

    ProductoEditForm impuestoEditForm
    Producto impuesto

    ProductoEditView() {
        setMargin(true);
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        impuesto = Grails.get(ProductoService).find(viewChangeEvent.getParameters())
        impuestoEditForm = new ProductoEditForm(impuesto)
        addComponent(impuestoEditForm)

        print(impuesto.nombre)
    }
}
