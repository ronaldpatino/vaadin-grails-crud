package com.sigecloud.modelo

class Impuesto {

    String nombre
    String codigoImpuesto
    String codigoPorcentaje

    BigDecimal porcentaje
    BigDecimal valor

    Boolean esPorcentajeValor = false

    String toString(){
        return this.nombre + "-" + this.codigoImpuesto + "-"  + this.codigoPorcentaje + " "
    }

    static constraints = {
    }
}
