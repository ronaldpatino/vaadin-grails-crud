package com.sigecloud.ui.factura.components

import com.sigecloud.componentes.SuggestingComboBox.SuggestingComboBox
import com.sigecloud.componentes.SuggestingComboBox.SuggestingContainer
import com.sigecloud.componentes.Sizer.Sizer
import com.sigecloud.modelo.Persona
import com.vaadin.data.Property
import com.vaadin.server.FontAwesome
import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.DateField
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Grid
import com.vaadin.ui.GridLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.TabSheet
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window


class FacturaCreateForm extends  CustomComponent implements Button.ClickListener, Property.ValueChangeListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")
    Button agregarElementoButton = new Button("Añadir un elemento")

    SuggestingComboBox nombre = new SuggestingComboBox("Nombre")
    DateField fecha = new DateField("Fecha de Factura")
    DateField fechaVencimiento = new DateField("Fecha de Vencimiento")

    TextArea observacion = new TextArea()

    TextField email = new TextField()
    TextField ruc = new TextField("RUC / Cédula")
    TextField claveAcceso = new TextField("Clave de Acceso")
    TextField numeroAutorizacion = new TextField("Número de Autorización")
    TextField numeroFactura = new TextField("Nº de factura del proveedor")



    TextField numeroFacturaPuntoEmision = new TextField()
    TextField numeroFacturaSecuecia = new TextField()

    Grid grid = new Grid();

    final SuggestingContainer container = new SuggestingContainer(Persona.class)

    FacturaCreateForm() {

        nombre.setImmediate(true)
        nombre.addValueChangeListener(this)
        nombre.setContainerDataSource(container)
        numeroFactura.setMaxLength(15)

        GridLayout cabeceraLayout = new GridLayout(2,4)
        cabeceraLayout.setSizeFull()
        VerticalLayout detalleVerticalLayout = new VerticalLayout()
        VerticalLayout verticalLayout = new VerticalLayout()

        /**
         * Botones
         */
        HorizontalLayout botonesLayout = new HorizontalLayout(guardarButton, new Sizer("1em", null) ,cancelButton)


        guardarButton.setStyleName("primary")
        guardarButton.setIcon(FontAwesome.FLOPPY_O)
        cancelButton.setIcon(FontAwesome.TIMES_CIRCLE_O)
        agregarElementoButton.setStyleName("quiet")
        agregarElementoButton.setIcon(FontAwesome.PLUS_CIRCLE)

        guardarButton.addClickListener(this)
        cancelButton.addClickListener(this)
        agregarElementoButton.addClickListener(this)
        /**
         * Fin Botones
         */


        FormLayout izquierda = new FormLayout()

        izquierda.addComponent(nombre)
        izquierda.addComponent(numeroFactura)
        izquierda.addComponent(claveAcceso)
        izquierda.addComponent(numeroAutorizacion)

        izquierda.addStyleName("light");
        izquierda.setMargin(true);


        FormLayout derecha = new FormLayout()

        derecha.addComponent(ruc)
        derecha.addComponent(fecha)
        derecha.addComponent(fechaVencimiento)
        derecha.addComponent(new Sizer(null, "1em"))

        derecha.addStyleName("light");
        derecha. setMargin(true);

        cabeceraLayout.addComponent(izquierda, 0, 1)
        cabeceraLayout.addComponent(derecha, 1, 1)



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

        facturaLayout.addComponent(new Sizer(null, "0.1em"))
        facturaLayout.addComponent(agregarElementoButton)
        facturaLayout.addComponent(new Sizer(null, "0.1em"))
        facturaLayout.addComponent(grid)




        HorizontalLayout pagosLayout = new HorizontalLayout()
        VerticalLayout observacionLayout = new VerticalLayout()

        observacionLayout.setSizeFull()
        observacion.setWidth("100%")
        observacionLayout.addComponent(new Sizer(null, "1em"))
        observacionLayout.addComponent(observacion)


        TabSheet detalleFacturaTab = new TabSheet()

        detalleFacturaTab.addTab(facturaLayout, "Factura")
        detalleFacturaTab.addTab(observacionLayout, "Observaciones")
        detalleFacturaTab.addTab(pagosLayout, "Pagos")

        detalleVerticalLayout.addComponent(detalleFacturaTab)


        verticalLayout.addComponent(botonesLayout)
        verticalLayout.addComponent(new Sizer(null, "1em"))
        verticalLayout.addComponent(cabeceraLayout)
        verticalLayout.addComponent(new Sizer(null, "1em"))
        verticalLayout.addComponent(detalleVerticalLayout)
        setCompositionRoot(verticalLayout);
    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {

        if(clickEvent.getSource() == agregarElementoButton){
            ItemAddForm itemAddForm = new ItemAddForm()
            Window agregarProductoWindow = new Window("<h4>Agregar Producto a la Factura</h4>");
            agregarProductoWindow.setCaptionAsHtml(true)
            agregarProductoWindow.setContent(itemAddForm);
            agregarProductoWindow.setModal(true);
            agregarProductoWindow.setClosable(false)
            agregarProductoWindow.setWidth("50%")

            UI.getCurrent().addWindow(agregarProductoWindow);
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
            ruc.readOnly = false
            ruc.setValue(persona.ruc)
            ruc.readOnly = true
        }


    }
}
