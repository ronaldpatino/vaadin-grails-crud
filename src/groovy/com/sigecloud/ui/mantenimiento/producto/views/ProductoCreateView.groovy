package com.sigecloud.ui.mantenimiento.producto.views

import com.sigecloud.ui.mantenimiento.producto.components.ProductoCreateForm
import com.sigecloud.ui.mantenimiento.producto.components.ProductoCreateOGForm
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout


class ProductoCreateView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "crearProducto";

    ProductoCreateView() {
        setMargin(true);
        ProductoCreateOGForm productoCreateForm = new ProductoCreateOGForm()
        addComponent(productoCreateForm)

    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
