package com.sigecloud.ui.mantenimiento.producto.components


import com.sigecloud.componentes.Sizer.Sizer
import com.sigecloud.mantenimiento.ProductoService
import com.sigecloud.mantenimiento.UnidadMedidaService
import com.sigecloud.modelo.Impuesto
import com.sigecloud.modelo.Producto
import com.sigecloud.modelo.UnidadMedida
import com.sigecloud.ui.mantenimiento.producto.views.ProductoListView
import com.sigecloud.util.ScNavigation
import com.vaadin.data.Property
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.data.validator.StringLengthValidator
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.server.Page
import com.vaadin.shared.Position
import com.vaadin.ui.*


class ProductoCreateOGForm extends CustomComponent implements Button.ClickListener, Property.ValueChangeListener {

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")
    Button agregarImpuestoButton = new Button("Añadir un Impuesto al Producto")
    TextField nombre = new TextField("Nombre")
    ComboBox unidadMedidaComboBox = new ComboBox("Unidad Medida");
    Grid impuestoGrid = new Grid();
    Producto producto = new Producto()
    BeanFieldGroup<Producto> formFieldBindings;
    List<Impuesto> impuestosList = new ArrayList<Impuesto>()
    //Collection<Impuesto> impuestosList = Lists.newArrayList()
    //Set<Impuesto> impuestosList = new HashSet<Impuesto>()


    ProductoCreateOGForm() {

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
        impuestoGrid.setContainerDataSource(new BeanItemContainer(Impuesto.class, impuestosList))
        impuestoGrid.removeAllColumns()
        impuestoGrid.addColumn("nombre")
        impuestoGrid.addColumn("codigoImpuesto")
        impuestoGrid.addColumn("codigoPorcentaje")
        impuestoGrid.addColumn("valor")
        impuestoGrid.addColumn("porcentaje")
        impuestoGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        impuestoGrid.setSizeFull();

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
        verticalLayout.addComponent(new Sizer(null, "1em"))
        Panel panelForm = new Panel()
        panelForm.setSizeFull()
        panelForm.setContent(formLayout)

        verticalLayout.addComponent(panelForm)
        verticalLayout.addComponent(new Sizer(null, "1em"))
        verticalLayout.addComponent(agregarImpuestoButton)
        verticalLayout.addComponent(new Sizer(null, "1em"))
        verticalLayout.addComponent(impuestoGrid)

        nombre.focus()

        setCompositionRoot(verticalLayout);

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {
        if (clickEvent.getSource() == guardarButton) {
            try {

                formFieldBindings.commit()
                Grails.get(ProductoService).save(producto)
                ScNavigation.navigateTo(ProductoListView.VIEW_NAME)
            }
            catch (FieldGroup.CommitException e) {
                Notification.show("You fail!" + e.getMessage());
            }
        }

        if (clickEvent.getSource() == agregarImpuestoButton) {
            ImpuestoAddOGWindow agregarImpuestoWindow = new ImpuestoAddOGWindow(new ProductoCreateOGForm.RespuestaModal() {
                @Override
                void impuesto(Impuesto impuesto) {

                    boolean es = false

                    for (Impuesto item : impuestosList){
                        if (item.nombre == impuesto.nombre){
                            es = true
                            break
                        }
                    }

                    if (!es){
                        impuestosList.add(impuesto)
                        impuestoGrid.setContainerDataSource(new BeanItemContainer(Impuesto.class, impuestosList))
                    }
                    else{
                        Notification notif = new Notification(
                                "Atención",
                                "Ya se encuentra agregado el impuesto: " + impuesto.nombre,
                                Notification.Type.HUMANIZED_MESSAGE,
                                true);

                        // Customize it
                        notif.setDelayMsec(20000);
                        notif.setPosition(Position.TOP_RIGHT);
                        notif.setIcon(FontAwesome.EXCLAMATION_CIRCLE);
                        notif.show(Page.getCurrent());

                    }

                }
            })

            UI.getCurrent().addWindow(agregarImpuestoWindow);

        }

        if (clickEvent.getSource() == cancelButton) {
            formFieldBindings.discard()
            ScNavigation.navigateTo(ProductoListView.VIEW_NAME)
        }

    }

    @Override
    void valueChange(Property.ValueChangeEvent valueChangeEvent) {

        if (valueChangeEvent.getProperty() == unidadMedidaComboBox) {
            producto.unidadMedida = (UnidadMedida) valueChangeEvent.getProperty().getValue()
        }

    }

    interface RespuestaModal {
        public void impuesto(Impuesto impuesto);
    }

}
