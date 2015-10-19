package com.sigecloud.ui.persona.views

import com.sigecloud.ui.persona.components.PersonaCreateForm
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout

/**
 * Created by ronald on 18/10/15.
 */
class PersonaCreateView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "crear";

    PersonaCreateView() {
        super();
        PersonaCreateForm persona = new PersonaCreateForm()
        addComponent(persona)

    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
