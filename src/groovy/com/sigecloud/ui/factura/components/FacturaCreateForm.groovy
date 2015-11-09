package com.sigecloud.ui.factura.components

import com.sigecloud.componentes.SuggestingComboBox.SuggestingComboBox
import com.sigecloud.componentes.SuggestingComboBox.SuggestingContainer
import com.sigecloud.modelo.Persona
import com.vaadin.data.Item
import com.vaadin.data.Property
import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.DateField
import com.vaadin.ui.GridLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.TabSheet
import com.vaadin.ui.Table

import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout


class FacturaCreateForm extends  CustomComponent implements Button.ClickListener, Property.ValueChangeListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")

    SuggestingComboBox nombre = new SuggestingComboBox()
    DateField fechaFactura = new DateField();
    TextField email = new TextField()

    Label ruc = new Label("")
    Label rucLabel = new Label("RUC / Cédula")
    Label nombreLabel = new Label("Nombre")
    Label fechaFacturaLabel = new Label("Fecha de Factura")
    Label numeroFacturaLabel = new Label("Nº de factura del proveedor")




    TextField numeroFacturaLocal = new TextField()
    TextField numeroFacturaPuntoEmision = new TextField()
    TextField numeroFacturaSecuecia = new TextField()
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
        /**
         * Fin Botones
         */

        HorizontalLayout nombreProveedorLayout = new HorizontalLayout()
        nombreLabel.setWidth("15em")
        nombre.setWidth("30em")
        nombreProveedorLayout.addComponent(nombreLabel)
        nombreProveedorLayout.addComponent(nombre)

        cabeceraLayout.addComponent(nombreProveedorLayout, 0,1)


        HorizontalLayout rucProveedorLayout = new HorizontalLayout()
        rucLabel.setWidth("15em")
        rucProveedorLayout.addComponent(rucLabel)
        rucProveedorLayout.addComponent(ruc)
        cabeceraLayout.addComponent(rucProveedorLayout, 1, 1)

        HorizontalLayout fechaFacturaLayout = new HorizontalLayout()
        fechaFacturaLabel.setWidth("15em")
        fechaFacturaLayout.addComponent(fechaFacturaLabel)
        fechaFacturaLayout.addComponent(fechaFactura)

        cabeceraLayout.addComponent(fechaFacturaLayout, 1,2)

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

        cabeceraLayout.addComponent(numeroFacturaProveedorLayout, 0,2 )


        Table table = new Table("Factura");


        table.setSizeFull()
// Define two columns for the built-in container
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Mag",  Float.class, null);

// Add a row the hard way
        Object newItemId = table.addItem();
        Item row1 = table.getItem(newItemId);
        row1.getItemProperty("Name").setValue("Sirius");
        row1.getItemProperty("Mag").setValue(-1.46f);





// Show exactly the currently contained rows (items)
        table.setPageLength(table.size());


        TabSheet detalleFacturaTab = new TabSheet();

        detalleFacturaTab.addTab(table,"Factura")


        detalleVerticalLayout.addComponent(detalleFacturaTab)

        verticalLayout.addComponent(botonesLayout)
        verticalLayout.addComponent(cabeceraLayout)
        verticalLayout.addComponent(detalleVerticalLayout)
        setCompositionRoot(verticalLayout);
    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {

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
