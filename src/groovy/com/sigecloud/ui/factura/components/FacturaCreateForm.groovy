package com.sigecloud.ui.factura.components

import com.sigecloud.componentes.SuggestingComboBox.SuggestingComboBox
import com.sigecloud.componentes.SuggestingComboBox.SuggestingContainer
import com.sigecloud.modelo.Persona
import com.vaadin.data.Item
import com.vaadin.data.Property
import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.DateField
import com.vaadin.ui.Grid
import com.vaadin.ui.GridLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.TabSheet
import com.vaadin.ui.Table

import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window


class FacturaCreateForm extends  CustomComponent implements Button.ClickListener, Property.ValueChangeListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")
    Button agregarElementoButton = new Button("Añadir un elemento")

    SuggestingComboBox nombre = new SuggestingComboBox()
    DateField fechaFactura = new DateField();
    TextField email = new TextField()
    Label ruc = new Label("")
    TextField claveDeAcceso = new TextField()
    TextField numeroAutorizacion = new TextField()
    TextField numeroFacturaLocal = new TextField()
    TextField numeroFacturaPuntoEmision = new TextField()
    TextField numeroFacturaSecuecia = new TextField()


    Label rucLabel = new Label("RUC / Cédula")
    Label nombreLabel = new Label("Nombre")
    Label fechaFacturaLabel = new Label("Fecha de Factura")
    Label numeroFacturaLabel = new Label("Nº de factura del proveedor")
    Label claveDeAccesoLabel = new Label("Clave de Acceso")
    Label numeroAutorizacionLabel = new Label("Número de Autorización")

    Grid grid = new Grid();

    final SuggestingContainer container = new SuggestingContainer(Persona.class)

    FacturaCreateForm() {


        nombre.setImmediate(true)
        nombre.addValueChangeListener(this)
        nombre.setContainerDataSource(container)

        GridLayout cabeceraLayout = new GridLayout(2,4)
        cabeceraLayout.setSizeFull()
        VerticalLayout detalleVerticalLayout = new VerticalLayout()
        VerticalLayout verticalLayout = new VerticalLayout()

        /**
         * Botones
         */
        HorizontalLayout botonesLayout = new HorizontalLayout(guardarButton, cancelButton)
        botonesLayout.setMargin(true)
        botonesLayout.setSpacing(true)
        guardarButton.addClickListener(this)
        cancelButton.addClickListener(this)
        agregarElementoButton.addClickListener(this)
        /**
         * Fin Botones
         */

        HorizontalLayout nombreProveedorLayout = new HorizontalLayout()
        nombreLabel.setWidth("15em")
        nombre.setWidth("30em")
        nombreProveedorLayout.addComponent(nombreLabel)
        nombreProveedorLayout.addComponent(nombre)


        HorizontalLayout rucProveedorLayout = new HorizontalLayout()
        rucLabel.setWidth("15em")
        rucProveedorLayout.addComponent(rucLabel)
        rucProveedorLayout.addComponent(ruc)

        HorizontalLayout fechaFacturaLayout = new HorizontalLayout()
        fechaFacturaLabel.setWidth("15em")
        fechaFacturaLayout.addComponent(fechaFacturaLabel)
        fechaFacturaLayout.addComponent(fechaFactura)


        HorizontalLayout numeroFacturaProveedorLayout = new HorizontalLayout()

        numeroFacturaLocal.setMaxLength(3)
        numeroFacturaLocal.setWidth("3em")
        numeroFacturaPuntoEmision.setMaxLength(3)
        numeroFacturaPuntoEmision.setWidth("3em")
        numeroFacturaSecuecia.setMaxLength(9)
        numeroFacturaSecuecia.setWidth("9em")
        numeroFacturaLabel.setWidth("15em")
        numeroFacturaProveedorLayout.addComponent(numeroFacturaLabel)
        numeroFacturaProveedorLayout.addComponent(numeroFacturaLocal)
        numeroFacturaProveedorLayout.addComponent(new Label(" - "))
        numeroFacturaProveedorLayout.addComponent(numeroFacturaPuntoEmision)
        numeroFacturaProveedorLayout.addComponent(new Label(" - "))
        numeroFacturaProveedorLayout.addComponent(numeroFacturaSecuecia)


        HorizontalLayout numeroDeAutorizacionLayout = new HorizontalLayout()
        numeroAutorizacionLabel.setWidth("15em")
        numeroAutorizacion.setWidth("30em")
        numeroDeAutorizacionLayout.addComponent(numeroAutorizacionLabel)
        numeroDeAutorizacionLayout.addComponent(numeroAutorizacion)


        HorizontalLayout claveDeAccesoLayout = new HorizontalLayout()
        claveDeAccesoLabel.setWidth("15em")
        claveDeAcceso.setWidth("30em")
        claveDeAccesoLayout.addComponent(claveDeAccesoLabel)
        claveDeAccesoLayout.addComponent(claveDeAcceso)

        cabeceraLayout.addComponent(nombreProveedorLayout, 0,1)
        cabeceraLayout.addComponent(rucProveedorLayout, 1, 1)
        cabeceraLayout.addComponent(fechaFacturaLayout, 1,2)
        cabeceraLayout.addComponent(numeroFacturaProveedorLayout, 0,2 )
        cabeceraLayout.addComponent(numeroDeAutorizacionLayout, 0,3)
        cabeceraLayout.addComponent(claveDeAccesoLayout, 1,3)



        VerticalLayout facturaLayout = new VerticalLayout()

        grid.setSizeFull();
        grid.setEditorEnabled(true);
        grid.setSelectionMode(Grid.SelectionMode.NONE);

        grid.addColumn("index", Integer.class).setHeaderCaption("##")
        grid.addColumn("codigo", String.class).setHeaderCaption("Codigo")
        grid.addColumn("descripcion", String.class).setHeaderCaption("Descripcion")
        grid.addColumn("cantidad", Integer.class).setHeaderCaption("Cantidad")
        grid.addColumn("precioUnitario", Integer.class).setHeaderCaption("Precio unitario")
        grid.addColumn("impuestos", Integer.class).setHeaderCaption("Impuestos")
        grid.addColumn("total", Integer.class).setHeaderCaption("Monto")


        facturaLayout.addComponent(grid)
        facturaLayout.addComponent(agregarElementoButton)


        HorizontalLayout pagosLayout = new HorizontalLayout()
        TabSheet detalleFacturaTab = new TabSheet()

        detalleFacturaTab.addTab(facturaLayout,"Factura")
        detalleFacturaTab.addTab(pagosLayout,"Pagos")


        detalleVerticalLayout.addComponent(detalleFacturaTab)

        verticalLayout.addComponent(botonesLayout)
        verticalLayout.addComponent(cabeceraLayout)
        verticalLayout.addComponent(detalleVerticalLayout)
        setCompositionRoot(verticalLayout);
    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {

        if(clickEvent.getSource() == agregarElementoButton){
            ItemAddForm itemAddForm = new ItemAddForm()
            //itemAddForm.setModal(true)
            //UI.getCurrent().addWindow(itemAddForm)
            Window sub = new Window("I'm Modal");
            sub.setContent(itemAddForm);
            sub.setModal(true);
            UI.getCurrent().addWindow(sub);
        }

    }

    @Override
    void valueChange(Property.ValueChangeEvent valueChangeEvent) {

        if(valueChangeEvent.getProperty() == nombre){
            Notification.show("Selected item: " + valueChangeEvent.getProperty().getValue(), Notification.Type.HUMANIZED_MESSAGE);
            // tell the custom container that a value has been selected. This is necessary to ensure that the
            // selected value is displayed by the ComboBox
            container.setSelectedCountryBean((Persona) valueChangeEvent.getProperty().getValue())
            Persona persona = new Persona()
            persona = (Persona) valueChangeEvent.getProperty().getValue()
            ruc.setValue(persona.ruc)
        }


    }
}
