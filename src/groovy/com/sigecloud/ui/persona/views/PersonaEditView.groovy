package com.sigecloud.ui.persona.views

import com.sigecloud.PersonaService
import com.sigecloud.modelo.Persona
import com.sigecloud.ui.persona.components.PersonaEditForm
import com.vaadin.grails.Grails
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout


class PersonaEditView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "editarPersona";

    PersonaEditForm personaEditForm
    Persona persona

    PersonaEditView() {
        setMargin(true);
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        persona = Grails.get(PersonaService).find(viewChangeEvent.getParameters())
        personaEditForm = new PersonaEditForm(persona)
        addComponent(personaEditForm)

        print(persona.nombre)
    }
}
