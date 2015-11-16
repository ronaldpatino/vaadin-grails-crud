package com.sigecloud.modelo

class Impuesto {

    String nombre
    String codigo
    String codigoPorcentaje

    BigDecimal porcentaje
    BigDecimal valor

    Boolean esPorcentaje = false
    Boolean esValor = false

    static constraints = {
    }
}
