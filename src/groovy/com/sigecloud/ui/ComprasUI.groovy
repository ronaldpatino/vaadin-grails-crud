package com.sigecloud.ui


import com.sigecloud.ui.compras.PersonaGrid
import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.UI

@Theme("valo")
class ComprasUI extends UI{

    PersonaGrid personaGrid = new PersonaGrid()
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        HorizontalLayout layout = new HorizontalLayout(personaGrid)
        layout.setMargin(true)
        layout.setSizeFull();
        setContent(layout)

    }
}
