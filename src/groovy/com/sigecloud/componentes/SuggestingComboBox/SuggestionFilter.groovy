package com.sigecloud.componentes.SuggestingComboBox

import com.vaadin.data.Container
import com.vaadin.data.Item


class SuggestionFilter  implements Container.Filter{

    private String filterString;

    public SuggestionFilter(String filterString) {
        this.filterString = filterString;
    }

    public String getFilterString() {
        return filterString;
    }

    @Override
    boolean passesFilter(Object o, Item item) throws UnsupportedOperationException {
        return false
    }

    @Override
    boolean appliesToProperty(Object o) {
        return false
    }
}
