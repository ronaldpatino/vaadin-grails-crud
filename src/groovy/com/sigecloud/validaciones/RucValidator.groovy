package com.sigecloud.validaciones

import com.vaadin.data.Validator


/**
 * Created by ronald on 18/10/15.
 */
class RucValidator implements Validator{
    @Override
    public void validate(Object o) throws Validator.InvalidValueException {

        String rucCedula = (String) o

        if(rucCedula == null) {
            throw new Validator.InvalidValueException("Ruc debe ser ingresado");
        }

        if (!rucCedula.matches("[0-9]+")) {
            throw new Validator.InvalidValueException("Ruc debe ser numerico");
        }

        else if (rucCedula.length() > 13  || rucCedula.length() < 10) {
            throw new Validator.InvalidValueException("Ruc largo incorrecto");
        }


    }
}
