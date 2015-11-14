package com.sigecloud.componetes.Sizer

import com.vaadin.ui.Label

/**
 * Created by ronald on 14/11/15.
 */
class Sizer extends Label {

    Sizer( String width, String height )
    {
        super();
        setWidth ( width );
        setHeight ( height );
    }
}
