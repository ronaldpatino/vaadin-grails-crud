package com.sigecloud.ui.compras

import com.sigecloud.PersonaService
import com.sigecloud.modelo.Persona
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.grails.Grails
import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Grid
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window


/**
 * Created by ronald on 15/10/15.
 */
class PersonaGrid extends CustomComponent {
    Grid personaGrid = new Grid()
    Button crearPersonaButton = new Button("Nueva Persona")
    Window personaWindow = new Window()

    public PersonaGrid() {



        crearPersonaButton.addClickListener(new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent clickEvent) {
                PersonaForm personaForm = new PersonaForm()
                personaWindow.setModal(true);
                personaWindow.setContent(personaForm)
                getUI().addWindow(personaWindow)
            }
        })

        HorizontalLayout buttonLayout = new HorizontalLayout(crearPersonaButton)
        buttonLayout.setMargin(true)
        buttonLayout.setSpacing(true)

        personaGrid.setContainerDataSource(new BeanItemContainer(Persona.class, Grails.get(PersonaService).getPersonas()))
        personaGrid.removeAllColumns()
        personaGrid.addColumn("ruc")
        personaGrid.addColumn("nombre")
        personaGrid.addColumn("nombreComercial")
        personaGrid.addColumn("direccion")
        personaGrid.addColumn("telefono")
        personaGrid.addColumn("tipoPersona")
        personaGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        personaGrid.setSizeFull()

        VerticalLayout layoutPersonaGrid = new VerticalLayout()

        layoutPersonaGrid.addComponent(buttonLayout)
        layoutPersonaGrid.addComponent(personaGrid)
        layoutPersonaGrid.setMargin(true)
        layoutPersonaGrid.setSizeFull()
        setCompositionRoot(layoutPersonaGrid);

    }

    def refreshPersonaGrid(){

        //@TODO agregar solo el row nuevo
        personaGrid.setContainerDataSource(new BeanItemContainer(Persona.class, Grails.get(PersonaService).getPersonas()))

    }
}
