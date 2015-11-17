package com.sigecloud.ui.mantenimiento.producto

import com.sigecloud.ui.comun.Modulo
import com.sigecloud.ui.mantenimiento.producto.views.ProductoCreateView
import com.sigecloud.ui.mantenimiento.producto.views.ProductoEditView
import com.sigecloud.ui.mantenimiento.producto.views.ProductoListView
import com.vaadin.annotations.Theme
import com.vaadin.navigator.Navigator
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.CssLayout
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@Theme("sigecloud")
class ProductoUI extends UI{


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        CssLayout topBar = new CssLayout()
        CssLayout viewLayout = new CssLayout()
        VerticalLayout layout = new VerticalLayout()

        topBar.setSizeFull()
        viewLayout.setSizeFull();


        topBar.addComponent(new Modulo("Productos"))

        layout.addComponent(topBar);
        layout.addComponent(viewLayout);
        layout.setSizeFull();
        layout.setMargin(false)

        layout.setExpandRatio(topBar, 0.1f)
        layout.setExpandRatio(viewLayout,4)

        setContent(layout);

        final Navigator navigator = new Navigator(this, viewLayout);
        navigator.addView("", ProductoListView.class);
        navigator.addView(ProductoListView.VIEW_NAME, ProductoListView.class);
        navigator.addView(ProductoCreateView.VIEW_NAME, ProductoCreateView.class);
        navigator.addView(ProductoEditView.VIEW_NAME, ProductoEditView.class);

    }
}
