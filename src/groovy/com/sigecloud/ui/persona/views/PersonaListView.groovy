package com.sigecloud.ui.persona.views

import com.sigecloud.ui.persona.components.PersonaCreateForm
import com.sigecloud.ui.persona.components.PersonaGrid
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout

/**
 * Created by ronald on 18/10/15.
 */
class PersonaListView extends VerticalLayout implements View{

    public static final String VIEW_NAME = "listar";

    PersonaListView() {
        super()
        addComponent(new PersonaGrid())

    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
