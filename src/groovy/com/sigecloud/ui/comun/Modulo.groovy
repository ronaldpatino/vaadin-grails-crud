package com.sigecloud.ui.comun

import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Panel

/**
 * Created by ronald on 18/10/15.
 */
class Modulo extends CustomComponent{

    Panel panel;

    Modulo(String nombreModulo) {
        panel = new Panel(nombreModulo)
        panel.setSizeFull()
        setCompositionRoot(panel);
    }

}
