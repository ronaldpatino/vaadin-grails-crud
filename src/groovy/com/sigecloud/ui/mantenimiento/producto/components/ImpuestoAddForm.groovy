package com.sigecloud.ui.mantenimiento.producto.components

import com.sigecloud.componentes.Sizer.Sizer
import com.sigecloud.mantenimiento.ImpuestoService
import com.sigecloud.mantenimiento.ProductoService
import com.sigecloud.modelo.Impuesto
import com.sigecloud.ui.mantenimiento.producto.views.ProductoListView
import com.sigecloud.util.ScNavigation
import com.vaadin.annotations.Theme
import com.vaadin.data.Property
import com.vaadin.grails.Grails
import com.vaadin.server.FontAwesome
import com.vaadin.ui.*

@Theme("sigecloud")
class ImpuestoAddForm extends CustomComponent  implements Property.ValueChangeListener{


    ComboBox  productoComboBox = new ComboBox("Producto");
    Impuesto  impuesto


    public ImpuestoAddForm() {
        super(); // Set window caption

        /**
         * Botones
         */

        productoComboBox.addValueChangeListener(this)


        // Some basic content for the window
        FormLayout content = new FormLayout();
        content.addStyleName("light");
        content.setMargin(true);

        def impuestos = Grails.get(ImpuestoService).getImpuestos()

        impuestos.each {
            impuesto -> productoComboBox.addItem(impuesto)
        }

        content.addComponent(productoComboBox)
        content.setMargin(true);
        setCompositionRoot(content);

    }


    @Override
    void valueChange(Property.ValueChangeEvent valueChangeEvent) {

        if (valueChangeEvent.getProperty() == productoComboBox){

            Object selected = productoComboBox.getValue()
            if (selected != null) {
                impuesto = (Impuesto)selected
            }
        }

    }
}
