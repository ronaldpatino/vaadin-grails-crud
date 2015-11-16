package com.sigecloud.ui.mantenimiento.impuesto

import com.sigecloud.ui.comun.Modulo
import com.sigecloud.ui.mantenimiento.impuesto.components.ImpuestoGrid
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoCreateView
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoEditView
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoListView
import com.vaadin.annotations.Theme
import com.vaadin.navigator.Navigator
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.CssLayout
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@Theme("sigecloud")
class ImpuestoUI extends UI{


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        CssLayout topBar = new CssLayout()
        CssLayout viewLayout = new CssLayout()
        VerticalLayout layout = new VerticalLayout()

        topBar.setSizeFull()
        viewLayout.setSizeFull();


        topBar.addComponent(new Modulo("Impuestos"))

        layout.addComponent(topBar);
        layout.addComponent(viewLayout);
        layout.setSizeFull();
        layout.setMargin(false)

        layout.setExpandRatio(topBar, 0.1f)
        layout.setExpandRatio(viewLayout,4)

        setContent(layout);

        final Navigator navigator = new Navigator(this, viewLayout);
        navigator.addView("", ImpuestoListView.class);
        navigator.addView(ImpuestoListView.VIEW_NAME, ImpuestoListView.class);
        navigator.addView(ImpuestoCreateView.VIEW_NAME, ImpuestoCreateView.class);
        navigator.addView(ImpuestoEditView.VIEW_NAME, ImpuestoEditView.class);

    }
}
