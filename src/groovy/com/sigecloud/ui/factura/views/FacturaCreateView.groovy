package com.sigecloud.ui.factura.views

import com.sigecloud.ui.factura.components.FacturaCreateForm
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout

class FacturaCreateView  extends VerticalLayout implements View{

    public static final String VIEW_NAME = "crearFactura";

    FacturaCreateView() {
        setMargin(true);
        FacturaCreateForm factura = new FacturaCreateForm()
        addComponent(factura)
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
