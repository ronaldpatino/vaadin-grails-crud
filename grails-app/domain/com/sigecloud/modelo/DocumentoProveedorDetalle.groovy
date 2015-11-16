package com.sigecloud.modelo

class DocumentoProveedorDetalle {

    BigDecimal cantidad
    BigDecimal costo
    BigDecimal valorDescuento
    BigDecimal valor

    static hasOne = [producto: Producto]

    static constraints = {
    }
}
