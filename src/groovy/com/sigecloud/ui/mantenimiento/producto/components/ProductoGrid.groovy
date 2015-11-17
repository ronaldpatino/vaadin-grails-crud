package com.sigecloud.ui.mantenimiento.producto.components


import com.sigecloud.componetes.Sizer.Sizer
import com.sigecloud.mantenimiento.ProductoService
import com.sigecloud.modelo.Producto
import com.sigecloud.ui.mantenimiento.producto.views.ProductoCreateView
import com.sigecloud.ui.mantenimiento.producto.views.ProductoEditView
import com.sigecloud.util.ScNavigation
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.event.SelectionEvent
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.*

class ProductoGrid extends CustomComponent implements SelectionEvent.SelectionListener, Button.ClickListener {

    Grid productoGrid = new Grid()
    Button crearProductoButton = new Button("Crear Nueva Producto")
    TextField buscarProducto = new TextField()
    Button buscarProductoButton = new Button()

    public ProductoGrid() {


        crearProductoButton.addClickListener(this)
        crearProductoButton.setIcon(FontAwesome.USER)
        crearProductoButton.setStyleName("primary")
        productoGrid.addSelectionListener(this)
        buscarProductoButton.addClickListener(this)
        buscarProducto.setInputPrompt("Buscar")
        buscarProductoButton.setIcon(FontAwesome.SEARCH)
        buscarProductoButton.setStyleName("primary")

        HorizontalLayout searchLayout = new HorizontalLayout(buscarProducto, buscarProductoButton)
        HorizontalLayout buttonLayout = new HorizontalLayout(crearProductoButton, searchLayout)
        buttonLayout.setComponentAlignment(searchLayout ,Alignment.MIDDLE_RIGHT)
        buttonLayout.setSizeFull()

        VerticalLayout verticalLayout = new VerticalLayout()

        productoGrid.setContainerDataSource(new BeanItemContainer(Producto.class, Grails.get(ProductoService).getProductos()))
        productoGrid.removeAllColumns()
        productoGrid.addColumn("nombre")
        productoGrid.addColumn("unidadMedida")


        productoGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        productoGrid.setSizeFull()

        verticalLayout.addComponent(buttonLayout)
        verticalLayout.addComponent (new Sizer(null, "1em"));
        verticalLayout.addComponent(productoGrid)
        verticalLayout.setSizeFull()

        setCompositionRoot(verticalLayout);



    }

    def refreshProductoGrid(){
        //@TODO agregar solo el row nuevo
        productoGrid.setContainerDataSource(new BeanItemContainer(Producto.class, Grails.get(ProductoService).getProductos()))

    }

    @Override
    void select(SelectionEvent selectionEvent) {

        Object selected = ((Grid.SingleSelectionModel) productoGrid.getSelectionModel()).getSelectedRow();
        if (selected != null) {

            ScNavigation.navigateTo(ProductoEditView.VIEW_NAME + "/" + productoGrid.getContainerDataSource().
                    getItem(selected)
                    .getItemProperty("id"))
        }
        else {
            Notification.show("Nothing selected");
        }

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {
        if (clickEvent.getSource() == crearProductoButton){
            ScNavigation.navigateTo(ProductoCreateView.VIEW_NAME)
        }
        else if (clickEvent.getSource() == buscarProductoButton){
            if (buscarProducto != null){

                String buscar = buscarProducto.getValue()
                if (buscar != null ){
                    BeanItemContainer<List> productos = new BeanItemContainer(Producto.class, Grails.get(ProductoService).search(buscar))

                    if (productos.size()>0){
                        productoGrid.setContainerDataSource(productos)
                    }
                    else {
                        Notification.show("No se encontraron resultados")
                    }
                }
            }


        }
    }
}
