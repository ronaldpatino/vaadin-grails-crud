package com.sigecloud.ui.persona.components

import com.sigecloud.PersonaService
import com.sigecloud.componetes.Sizer.Sizer
import com.sigecloud.modelo.Persona
import com.sigecloud.ui.persona.views.PersonaCreateView
import com.sigecloud.ui.persona.views.PersonaEditView
import com.sigecloud.util.ScNavigation
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.event.SelectionEvent
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.*

class PersonaGrid extends CustomComponent implements SelectionEvent.SelectionListener, Button.ClickListener {

    Grid personaGrid = new Grid()
    Button crearPersonaButton = new Button("Crear Nueva Persona")
    TextField buscarPersona = new TextField()
    Button buscarPersonaButton = new Button()
    Window personaWindow = new Window()


    public PersonaGrid() {


        crearPersonaButton.addClickListener(this)
        crearPersonaButton.setIcon(FontAwesome.USER)
        crearPersonaButton.setStyleName("primary")
        personaGrid.addSelectionListener(this)
        buscarPersonaButton.addClickListener(this)
        buscarPersona.setInputPrompt("Buscar")
        buscarPersonaButton.setIcon(FontAwesome.SEARCH)
        buscarPersonaButton.setStyleName("primary")

        HorizontalLayout searchLayout = new HorizontalLayout(buscarPersona, buscarPersonaButton)
        HorizontalLayout buttonLayout = new HorizontalLayout(crearPersonaButton, searchLayout)
        buttonLayout.setComponentAlignment(searchLayout ,Alignment.MIDDLE_RIGHT)
        buttonLayout.setSizeFull()

        VerticalLayout verticalLayout = new VerticalLayout()

        personaGrid.setContainerDataSource(new BeanItemContainer(Persona.class, Grails.get(PersonaService).getPersonas()))
        personaGrid.removeAllColumns()
        personaGrid.addColumn("ruc")
        personaGrid.addColumn("nombre")
        personaGrid.addColumn("nombreComercial")
        personaGrid.addColumn("direccion")
        personaGrid.addColumn("telefono")
        personaGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        personaGrid.setSizeFull()

        verticalLayout.addComponent(buttonLayout)
        verticalLayout.addComponent (new Sizer(null, "1em"));
        verticalLayout.addComponent(personaGrid)
        verticalLayout.setSizeFull()

        setCompositionRoot(verticalLayout);



    }

    def refreshPersonaGrid(){
        //@TODO agregar solo el row nuevo
        personaGrid.setContainerDataSource(new BeanItemContainer(Persona.class, Grails.get(PersonaService).getPersonas()))

    }

    @Override
    void select(SelectionEvent selectionEvent) {

        Object selected = ((Grid.SingleSelectionModel) personaGrid.getSelectionModel()).getSelectedRow();
        if (selected != null) {

            ScNavigation.navigateTo(PersonaEditView.VIEW_NAME + "/" + personaGrid.getContainerDataSource().
                    getItem(selected)
                    .getItemProperty("id"))
        }
        else {
            Notification.show("Nothing selected");
        }

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {
        if (clickEvent.getSource() == crearPersonaButton){
            ScNavigation.navigateTo(PersonaCreateView.VIEW_NAME)
        }
        else if (clickEvent.getSource() == buscarPersonaButton){
            if (buscarPersona != null){

                String buscar = buscarPersona.getValue()
                if (buscar != null ){
                    BeanItemContainer<List> personas = new BeanItemContainer(Persona.class, Grails.get(PersonaService).search(buscar))

                    if (personas.size()>0){
                        personaGrid.setContainerDataSource(personas)
                    }
                    else {
                        Notification.show("No se encontraron resultados")
                    }
                }
            }


        }
    }
}
