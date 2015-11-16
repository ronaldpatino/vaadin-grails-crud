package com.sigecloud.ui.general

import com.sigecloud.modelo.Impuesto
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoCreateView
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoEditView
import com.sigecloud.ui.mantenimiento.impuesto.views.ImpuestoListView
import com.sigecloud.ui.persona.views.PersonaCreateView
import com.sigecloud.ui.persona.views.PersonaEditView
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Component

import java.util.Map.Entry;


import com.sigecloud.ui.factura.views.FacturaCreateView
import com.sigecloud.ui.persona.views.PersonaListView
import com.vaadin.navigator.Navigator
import com.vaadin.server.Page
import com.vaadin.ui.ComponentContainer


import com.sigecloud.ui.general.componentes.ScMenuLayout
import com.vaadin.annotations.Theme
import com.vaadin.server.ThemeResource
import com.vaadin.server.VaadinRequest
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.CssLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.MenuBar
import com.vaadin.ui.UI
import com.vaadin.ui.themes.ValoTheme

@Theme("sigecloud")
class GeneralUI extends UI {

    ScMenuLayout root = new ScMenuLayout();
    CssLayout menu = new CssLayout();
    CssLayout menuItemsLayout = new CssLayout();
    private final LinkedHashMap<String, String> menuItems = new LinkedHashMap<String, String>();
    private Navigator navigator;
    ComponentContainer viewDisplay = root.getContentContainer();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("SigeCloud");
        setContent(root);
        root.setWidth("100%");
        root.addMenu(buildMenu());


        addStyleName(ValoTheme.UI_WITH_MENU);

        navigator = new Navigator(this, viewDisplay);
        navigator.addView(FacturaCreateView.VIEW_NAME, FacturaCreateView.class);

        navigator.addView(PersonaListView.VIEW_NAME, PersonaListView.class);
        navigator.addView(PersonaCreateView.VIEW_NAME, PersonaCreateView.class);
        navigator.addView(PersonaEditView.VIEW_NAME, PersonaEditView.class);

        navigator.addView(ImpuestoListView.VIEW_NAME, ImpuestoListView.class);
        navigator.addView(ImpuestoCreateView.VIEW_NAME, ImpuestoCreateView.class);
        navigator.addView(ImpuestoEditView.VIEW_NAME, ImpuestoEditView.class);


        final String f = Page.getCurrent().getUriFragment();

        if (f == null || f.equals("")) {
            navigator.navigateTo(PersonaListView.VIEW_NAME);
        }

        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            boolean beforeViewChange(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
                return true
            }

            @Override
            void afterViewChange(ViewChangeListener.ViewChangeEvent event) {
                for (final Iterator<Component> it = menuItemsLayout.iterator(); it
                        .hasNext();) {
                    it.next().removeStyleName("selected");
                }
                for (final Entry<String, String> item : menuItems.entrySet()) {
                    if (event.getViewName().equals(item.getKey())) {
                        for (final Iterator<Component> it = menuItemsLayout
                                .iterator(); it.hasNext();) {
                            final Component c = it.next();
                            if (c.getCaption() != null
                                    && c.getCaption().startsWith(
                                    item.getValue())) {
                                c.addStyleName("selected");
                                break;
                            }
                        }
                        break;
                    }
                }
                menu.removeStyleName("valo-menu-visible");
            }
        })
    }


    CssLayout buildMenu() {
        // Add items
        menuItems.put(PersonaListView.VIEW_NAME, "Personas");
        menuItems.put(FacturaCreateView.VIEW_NAME, "Facturas");
        menuItems.put(ImpuestoListView.VIEW_NAME, "Impuestos");




        final HorizontalLayout top = new HorizontalLayout();
        top.setWidth("20em");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName("valo-menu-title");
        menu.addComponent(top);


        final Label title = new Label("<h3>SigeCloud</h3>", ContentMode.HTML);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);

        // Menu usuario
        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        final MenuBar.MenuItem settingsItem = settings.addItem("NOMBRE USUARIO", new ThemeResource("../sigecloud/profile-pic-300px.jpg"), null);
        settingsItem.addItem("Edit Profile", null);
        settingsItem.addItem("Preferences", null);
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", null);
        menu.addComponent(settings);

        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);

        Label label = null

        for (Entry<String, String> item : menuItems.entrySet()) {

            if (item.getKey().equals(ImpuestoListView.VIEW_NAME)) {
                label = new Label("Mantenimientos", ContentMode.HTML);
                label.setPrimaryStyleName("valo-menu-subtitle");
                label.addStyleName("h4");
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);
            }

            String key = item.getKey()
            final Button b = new Button(item.getValue(), new Button.ClickListener() {
                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    navigator.navigateTo(key);
                }
            });

            b.setHtmlContentAllowed(true);
            b.setPrimaryStyleName("valo-menu-item");
            menuItemsLayout.addComponent(b);
        }


        return menu;
    }
}
