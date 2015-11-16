package com.sigecloud.ui.mantenimiento.impuesto.views

import com.sigecloud.ui.mantenimiento.impuesto.components.ImpuestoCreateForm
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout

/**
 * Created by ronald on 18/10/15.
 */
class ImpuestoCreateView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "crearImpuesto";

    ImpuestoCreateView() {
        setMargin(true);
        ImpuestoCreateForm persona = new ImpuestoCreateForm()
        addComponent(persona)

    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
