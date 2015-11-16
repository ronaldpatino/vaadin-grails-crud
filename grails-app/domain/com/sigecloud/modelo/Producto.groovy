package com.sigecloud.modelo

class Producto {

    String nombre

    static hasOne = [unidadMedida: UnidadMedida]
    static hasMany = [impuestos: Impuesto]

    static constraints = {
    }
}
