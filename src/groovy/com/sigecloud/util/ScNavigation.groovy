package com.sigecloud.util

import com.sigecloud.ui.persona.views.PersonaListView
import com.vaadin.navigator.Navigator
import com.vaadin.ui.UI

/**
 * Created by ronald on 18/10/15.
 */
class ScNavigation {

    public static navigateTo(String view){
        UI ui = UI.getCurrent();
        Navigator navigator = ui.getNavigator();
        navigator.navigateTo(view);
    }
}
