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
import com.vaadin.ui.Notification
import com.vaadin.ui.Table
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout


class FacturaCreateForm extends  CustomComponent implements Button.ClickListener, Property.ValueChangeListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")

    SuggestingComboBox nombre = new SuggestingComboBox("Nombre")
    DateField fechaFactura = new DateField("Fecha de la Factura");

    TextField ruc = new TextField("RUC / Cédula")
    TextField email = new TextField("Email")


    TextField numeroFactura = new TextField("Nro. Factura del Proveedor")
    final SuggestingContainer container = new SuggestingContainer(Persona.class)

    FacturaCreateForm() {


        nombre.setImmediate(true)



        nombre.addValueChangeListener(this)
        nombre.setContainerDataSource(container)

        GridLayout cabeceraVerticalLayout = new GridLayout(2,4)
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


        cabeceraVerticalLayout.addComponent(nombre, 0,1)
        cabeceraVerticalLayout.addComponent(ruc, 1, 1)

        cabeceraVerticalLayout.addComponent(fechaFactura, 1,2)
        cabeceraVerticalLayout.addComponent(numeroFactura, 0,2 )



        Table table = new Table("The Brightest Stars");

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

        detalleVerticalLayout.addComponent(table)

        verticalLayout.addComponent(botonesLayout)
        verticalLayout.addComponent(cabeceraVerticalLayout)
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
