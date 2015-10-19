package com.sigecloud.ui.persona

import com.sigecloud.ui.comun.Modulo
import com.sigecloud.ui.persona.components.PersonaGrid
import com.sigecloud.ui.persona.views.PersonaCreateView
import com.sigecloud.ui.persona.views.PersonaEditView
import com.sigecloud.ui.persona.views.PersonaListView
import com.vaadin.annotations.Theme
import com.vaadin.navigator.Navigator
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.CssLayout
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@Theme("valo")
class PersonaUI extends UI{

    PersonaGrid personaGrid = new PersonaGrid()

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        CssLayout topBar = new CssLayout()
        CssLayout viewLayout = new CssLayout()
        VerticalLayout layout = new VerticalLayout()

        topBar.setSizeFull()
        viewLayout.setSizeFull();


        topBar.addComponent(new Modulo("Personas"))

        layout.addComponent(topBar);
        layout.addComponent(viewLayout);
        layout.setSizeFull();
        layout.setMargin(false)

        layout.setExpandRatio(topBar, 0.1f)
        layout.setExpandRatio(viewLayout,4)

        setContent(layout);

        final Navigator navigator = new Navigator(this, viewLayout);
        navigator.addView("", PersonaListView.class);
        navigator.addView(PersonaListView.VIEW_NAME, PersonaListView.class);
        navigator.addView(PersonaCreateView.VIEW_NAME, PersonaCreateView.class);
        navigator.addView(PersonaEditView.VIEW_NAME, PersonaEditView.class);

    }
}
