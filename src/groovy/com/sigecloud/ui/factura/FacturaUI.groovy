package com.sigecloud.ui.factura

import com.sigecloud.ui.comun.Modulo
import com.sigecloud.ui.factura.components.FacturaCreateForm
import com.sigecloud.ui.factura.views.FacturaCreateView
import com.vaadin.annotations.Theme
import com.vaadin.navigator.Navigator
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.CssLayout
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@Theme("sigecloud")
class FacturaUI extends UI{

    FacturaCreateForm facturaCreateForm = new FacturaCreateForm()

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout topBar = new VerticalLayout()
        VerticalLayout viewLayout = new VerticalLayout()
        VerticalLayout layout = new VerticalLayout()

        topBar.setSizeFull()
        viewLayout.setSizeFull();


        topBar.addComponent(new Modulo("Facturas"))

        layout.addComponent(topBar);
        layout.addComponent(viewLayout);
        layout.setSizeFull();
        layout.setMargin(false)

        layout.setExpandRatio(topBar, 0.1f)
        layout.setExpandRatio(viewLayout,4)

        setContent(layout);

        final Navigator navigator = new Navigator(this, viewLayout);
        navigator.addView("", FacturaCreateView.class);
        navigator.addView(FacturaCreateView.VIEW_NAME, FacturaCreateView.class);

    }
}
