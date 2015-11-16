package com.sigecloud.ui.mantenimiento.impuesto.components


import com.sigecloud.componetes.Sizer.Sizer
import com.sigecloud.mantenimiento.ImpuestoService
import com.sigecloud.modelo.Impuesto
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoCreateView
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoEditView
import com.sigecloud.util.ScNavigation
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.event.SelectionEvent
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.*

class ImpuestoGrid extends CustomComponent implements SelectionEvent.SelectionListener, Button.ClickListener {

    Grid impuestoGrid = new Grid()
    Button crearImpuestoButton = new Button("Crear Nueva Impuesto")
    TextField buscarImpuesto = new TextField()
    Button buscarImpuestoButton = new Button()

    public ImpuestoGrid() {


        crearImpuestoButton.addClickListener(this)
        crearImpuestoButton.setIcon(FontAwesome.USER)
        crearImpuestoButton.setStyleName("primary")
        impuestoGrid.addSelectionListener(this)
        buscarImpuestoButton.addClickListener(this)
        buscarImpuesto.setInputPrompt("Buscar")
        buscarImpuestoButton.setIcon(FontAwesome.SEARCH)
        buscarImpuestoButton.setStyleName("primary")

        HorizontalLayout searchLayout = new HorizontalLayout(buscarImpuesto, buscarImpuestoButton)
        HorizontalLayout buttonLayout = new HorizontalLayout(crearImpuestoButton, searchLayout)
        buttonLayout.setComponentAlignment(searchLayout ,Alignment.MIDDLE_RIGHT)
        buttonLayout.setSizeFull()

        VerticalLayout verticalLayout = new VerticalLayout()

        impuestoGrid.setContainerDataSource(new BeanItemContainer(Impuesto.class, Grails.get(ImpuestoService).getImpuestos()))
        impuestoGrid.removeAllColumns()
        impuestoGrid.addColumn("nombre")
        impuestoGrid.addColumn("codigoImpuesto")
        impuestoGrid.addColumn("codigoPorcentaje")
        impuestoGrid.addColumn("porcentaje")
        impuestoGrid.addColumn("valor")

        impuestoGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        impuestoGrid.setSizeFull()

        verticalLayout.addComponent(buttonLayout)
        verticalLayout.addComponent (new Sizer(null, "1em"));
        verticalLayout.addComponent(impuestoGrid)
        verticalLayout.setSizeFull()

        setCompositionRoot(verticalLayout);



    }

    def refreshImpuestoGrid(){
        //@TODO agregar solo el row nuevo
        impuestoGrid.setContainerDataSource(new BeanItemContainer(Impuesto.class, Grails.get(ImpuestoService).getImpuestos()))

    }

    @Override
    void select(SelectionEvent selectionEvent) {

        Object selected = ((Grid.SingleSelectionModel) impuestoGrid.getSelectionModel()).getSelectedRow();
        if (selected != null) {

            ScNavigation.navigateTo(ImpuestoEditView.VIEW_NAME + "/" + impuestoGrid.getContainerDataSource().
                    getItem(selected)
                    .getItemProperty("id"))
        }
        else {
            Notification.show("Nothing selected");
        }

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {
        if (clickEvent.getSource() == crearImpuestoButton){
            ScNavigation.navigateTo(ImpuestoCreateView.VIEW_NAME)
        }
        else if (clickEvent.getSource() == buscarImpuestoButton){
            if (buscarImpuesto != null){

                String buscar = buscarImpuesto.getValue()
                if (buscar != null ){
                    BeanItemContainer<List> impuestos = new BeanItemContainer(Impuesto.class, Grails.get(ImpuestoService).search(buscar))

                    if (impuestos.size()>0){
                        impuestoGrid.setContainerDataSource(impuestos)
                    }
                    else {
                        Notification.show("No se encontraron resultados")
                    }
                }
            }


        }
    }
}
