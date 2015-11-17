package com.sigecloud.ui.mantenimiento.producto.views

import com.sigecloud.ui.mantenimiento.producto.components.ProductoCreateForm
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout

/**
 * Created by ronald on 18/10/15.
 */
class ProductoCreateView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "crearProducto";

    ProductoCreateView() {
        setMargin(true);
        ProductoCreateForm productoCreateForm = new ProductoCreateForm()
        addComponent(productoCreateForm)

    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
