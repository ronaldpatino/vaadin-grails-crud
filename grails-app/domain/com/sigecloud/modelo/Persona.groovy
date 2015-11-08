package com.sigecloud.modelo
import org.apache.commons.lang.WordUtils


class Persona {

    String nombre
    String nombreComercial
    String ruc
    String direccion
    String email
    String telefono
    String celular
    Boolean esCliente = false
    Boolean esProveedor = false

    static constraints = {
        nombre maxSize: 255
        nombreComercial maxSize: 255
        ruc maxSize: 13
        direccion maxSize: 500
        email maxSize: 255
        telefono maxSize: 100
        celular maxSize: 100
        esCliente defaulValue: "false"
        esProveedor defaulValue: "false"

    }

    void setNombre(String s){
        nombre = WordUtils.capitalizeFully(s)
    }

    void setNombreComercial(String s){
        nombreComercial = WordUtils.capitalizeFully(s)
    }

    void setDireccion(String s){
        direccion = WordUtils.capitalizeFully(s)
    }

    void setEmail(String s){
        email = s?.toLowerCase()
    }
}
