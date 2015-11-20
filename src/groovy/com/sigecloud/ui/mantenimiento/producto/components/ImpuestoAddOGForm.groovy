package com.sigecloud.ui.mantenimiento.producto.components


import com.sigecloud.modelo.Impuesto
import com.vaadin.annotations.Theme
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.VerticalLayout

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
