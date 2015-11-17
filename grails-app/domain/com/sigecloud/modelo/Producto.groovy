package com.sigecloud.modelo

class Producto {

    String nombre
    UnidadMedida unidadMedida

    static hasMany = [impuestos: Impuesto]

    static mapping = {
        unidadMedida lazy: false
    }

    static constraints = {
    }
}
