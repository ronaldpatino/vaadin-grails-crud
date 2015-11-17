package com.sigecloud.ui.mantenimiento.producto.views

import com.sigecloud.ui.mantenimiento.producto.components.ProductoGrid
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout

/**
 * Created by ronald on 18/10/15.
 */
class ProductoListView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "listarProducto";

    ProductoListView() {
        setMargin(true);
        addComponent(new ProductoGrid())

    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
