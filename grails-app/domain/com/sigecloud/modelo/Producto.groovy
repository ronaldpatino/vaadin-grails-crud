package com.sigecloud.modelo

class Producto {

    String nombre
    UnidadMedida unidadMedida

    String toString(){
        return this.nombre
    }

    static hasMany = [impuestos: Impuesto]

    static mapping = {
        unidadMedida lazy: false
    }

    static constraints = {
    }
}
