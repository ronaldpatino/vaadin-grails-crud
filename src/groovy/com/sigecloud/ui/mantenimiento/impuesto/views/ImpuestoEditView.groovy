package com.sigecloud.ui.mantenimiento.impuesto.views

import com.sigecloud.mantenimiento.ImpuestoService
import com.sigecloud.modelo.Impuesto
import com.sigecloud.ui.mantenimiento.impuesto.components.ImpuestoEditForm
import com.vaadin.grails.Grails
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout


class ImpuestoEditView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "editarImpuesto";

    ImpuestoEditForm impuestoEditForm
    Impuesto impuesto

    ImpuestoEditView() {
        setMargin(true);
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        impuesto = Grails.get(ImpuestoService).find(viewChangeEvent.getParameters())
        impuestoEditForm = new ImpuestoEditForm(impuesto)
        addComponent(impuestoEditForm)

        print(impuesto.nombre)
    }
}
