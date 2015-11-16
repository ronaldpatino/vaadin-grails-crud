package com.sigecloud.modelo

class Impuesto {

    String nombre
    String codigoImpuesto
    String codigoPorcentaje

    BigDecimal porcentaje
    BigDecimal valor

    Boolean esPorcentaje = false
    Boolean esValor = false

    static constraints = {
    }
}
