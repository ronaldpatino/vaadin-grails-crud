package com.sigecloud.ui.mantenimiento.producto.components

import com.sigecloud.mantenimiento.ImpuestoService
import com.sigecloud.modelo.Impuesto
import com.vaadin.annotations.Theme
import com.vaadin.data.Property
import com.vaadin.grails.Grails
import com.vaadin.ui.ComboBox
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.OptionGroup

@Theme("sigecloud")
class ImpuestoAddOGForm extends CustomComponent{

    Impuesto  impuesto
    ImpuestoGrid impuestoGrid = new ImpuestoGrid()


    public ImpuestoAddOGForm() {
        super();

        VerticalLayout content = new VerticalLayout();
        content.addStyleName("light");
        content.setMargin(true);
        content.addComponent(impuestoGrid)
        setCompositionRoot(content);

    }

}
