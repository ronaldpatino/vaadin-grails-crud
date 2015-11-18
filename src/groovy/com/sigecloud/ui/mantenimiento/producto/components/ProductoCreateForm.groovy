package com.sigecloud.ui.mantenimiento.producto.components

import com.sigecloud.componentes.Sizer.Sizer
import com.sigecloud.mantenimiento.ProductoService
import com.sigecloud.mantenimiento.UnidadMedidaService
import com.sigecloud.modelo.Producto
import com.sigecloud.modelo.UnidadMedida
import com.sigecloud.ui.factura.components.ItemAddForm
import com.sigecloud.ui.mantenimiento.producto.views.ProductoListView
import com.sigecloud.util.ScNavigation
import com.vaadin.data.Property
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.validator.StringLengthValidator
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.*

class ProductoCreateForm extends CustomComponent implements Button.ClickListener, Property.ValueChangeListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")
    Button agregarImpuestoButton = new Button("Añadir un Impuesto al Producto")
    TextField nombre = new TextField("Nombre")
    ComboBox unidadMedidaComboBox = new ComboBox("Unidad Medida");
    Grid impuestoGrid = new Grid();
    Producto producto = new Producto()
    BeanFieldGroup<Producto> formFieldBindings;
    def impuestosList = []


    ProductoCreateForm() {

        /**
         * Depende de donde llames ponemos true o false
         */

        VerticalLayout verticalLayout = new VerticalLayout()

        /**
         * Botones
         */
        guardarButton.setStyleName("primary")
        guardarButton.setIcon(FontAwesome.FLOPPY_O)
        cancelButton.setIcon(FontAwesome.TIMES_CIRCLE_O)

        agregarImpuestoButton.setStyleName("quiet")
        agregarImpuestoButton.setIcon(FontAwesome.PLUS_CIRCLE)

        HorizontalLayout botonesLayout = new HorizontalLayout(guardarButton, new Sizer("1em", null), cancelButton)

        guardarButton.addClickListener(this)
        cancelButton.addClickListener(this)
        agregarImpuestoButton.addClickListener(this)
        unidadMedidaComboBox.addValueChangeListener(this)


        /**
         * Fin Botones
         */


        FormLayout formLayout = new FormLayout();

        impuestoGrid.setSizeFull();
        impuestoGrid.setEditorEnabled(true);
        impuestoGrid.setSelectionMode(Grid.SelectionMode.NONE);

        impuestoGrid.addColumn("nombre", String.class).setHeaderCaption("Nombre")
        impuestoGrid.addColumn("codigoImpuesto", String.class).setHeaderCaption("Codigo Impuesto")
        impuestoGrid.addColumn("codigoPorcentaje", String.class).setHeaderCaption("Codigo Porcentaje")


        def unidadesMedida = Grails.get(UnidadMedidaService).getUnidadMedidas()

        unidadesMedida.each {
            um -> unidadMedidaComboBox.addItem(um)
        }


        formLayout.addStyleName("light");
        formLayout.setMargin(true);
        formLayout.addComponent(nombre)
        formLayout.addComponent(unidadMedidaComboBox)

        //Valores por defecto para form
        nombre.setNullRepresentation("")

        /**
         * VALIDACIONES
         */

        nombre.addValidator(new StringLengthValidator("Debe ingresar un nombre", 3, 255, false))
        nombre.setImmediate(true);

        /**
         * FIN VALIDACIONES
         */

        /**
         * CONSTRAINTS
         */
        nombre.setMaxLength(255)

        /**
         * FIN CONSTRAINTS
         */

        /**
         * BINDING
         *
         * Debes enlazar los campos del objeto actual al bean para que funcione ojo
         * el this hace referecia a los campos de este objeto asi los relaciona
         */
        formFieldBindings = BeanFieldGroup.bindFieldsBuffered(producto, this)

        verticalLayout.addComponent(botonesLayout)
        verticalLayout.addComponent(new Sizer(null,"1em"))
        Panel panelForm = new Panel()
        panelForm.setSizeFull()
        panelForm.setContent(formLayout)
        
        verticalLayout.addComponent(panelForm)
        verticalLayout.addComponent(new Sizer(null, "1em"))
        verticalLayout.addComponent(agregarImpuestoButton)
        verticalLayout.addComponent(new Sizer(null, "1em"))
        verticalLayout.addComponent(impuestoGrid)

        setCompositionRoot(verticalLayout);

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {
        if (clickEvent.getSource() == guardarButton){
            try{

                formFieldBindings.commit()
                Grails.get(ProductoService).save(producto)
                ScNavigation.navigateTo(ProductoListView.VIEW_NAME)
            }
            catch (FieldGroup.CommitException e) {
                Notification.show("You fail!" + e.getMessage());
            }
        }

        if(clickEvent.getSource() == agregarImpuestoButton){
            ImpuestoAddWindow agregarImpuestoWindow = new ImpuestoAddWindow()
            UI.getCurrent().addWindow(agregarImpuestoWindow);
            print "HELLO"
        }

        if (clickEvent.getSource() == cancelButton){
            formFieldBindings.discard()
            ScNavigation.navigateTo(ProductoListView.VIEW_NAME)
        }



    }

    @Override
    void valueChange(Property.ValueChangeEvent valueChangeEvent) {

        if(valueChangeEvent.getProperty() == unidadMedidaComboBox){
            producto.unidadMedida = (UnidadMedida) valueChangeEvent.getProperty().getValue()
        }

    }
}
