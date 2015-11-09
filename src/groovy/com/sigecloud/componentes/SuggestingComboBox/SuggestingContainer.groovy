package com.sigecloud.componentes.SuggestingComboBox

import com.sigecloud.PersonaService
import com.sigecloud.modelo.Persona
import com.vaadin.data.Container.Filter
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.data.util.filter.UnsupportedFilterException
import com.vaadin.grails.Grails


class SuggestingContainer extends BeanItemContainer<Persona> {

    SuggestingContainer(Class<? super Persona> type) throws IllegalArgumentException {
        super(type)
    }

    @Override
    protected void addFilter(Filter filter) throws UnsupportedFilterException {
        SuggestionFilter suggestionFilter = (SuggestionFilter) filter;
        filterItems(suggestionFilter.getFilterString());
    }


    private void filterItems(String filterString) {

        if ("".equals(filterString)) {
            return;
        }

        if (filterString.length() >= 3){
            removeAllItems();
            List<Persona> personas = Grails.get(PersonaService).search(filterString);
            addAll(personas);
        }

    }

    public void setSelectedCountryBean(Persona persona) {
        removeAllItems();
        addBean(persona);
    }



}
