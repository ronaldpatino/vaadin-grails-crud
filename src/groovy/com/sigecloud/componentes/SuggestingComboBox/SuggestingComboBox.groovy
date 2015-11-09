package com.sigecloud.componentes.SuggestingComboBox

import com.vaadin.data.Container.Filter
import com.vaadin.shared.ui.combobox.FilteringMode
import com.vaadin.ui.ComboBox


class SuggestingComboBox extends  ComboBox{

    public SuggestingComboBox(String caption) {
        super(caption);
        // the item caption mode has to be PROPERTY for the filtering to work
        setItemCaptionMode(itemCaptionMode.PROPERTY);

        // define the property name of the CountryBean to use as item caption
        setItemCaptionPropertyId("nombre");
    }

    public SuggestingComboBox() {
        this(null);
    }

    @Override
    protected Filter buildFilter(String filterString, FilteringMode filteringMode) {
        return new SuggestionFilter(filterString);
    }

}
