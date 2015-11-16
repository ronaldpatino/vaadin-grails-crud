package com.sigecloud.ui.mantenimiento.impuesto.views

import com.sigecloud.ui.mantenimiento.impuesto.components.ImpuestoGrid
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout

/**
 * Created by ronald on 18/10/15.
 */
class ImpuestoListView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "listarImpuesto";

    ImpuestoListView() {
        setMargin(true);
        addComponent(new ImpuestoGrid())

    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
