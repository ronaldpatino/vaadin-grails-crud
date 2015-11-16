package com.sigecloud.modelo

class DocumentoProveedorDetalle {

    BigDecimal cantidad
    BigDecimal costo
    BigDecimal valorDescuento
    BigDecimal valor

    Producto producto

    static belongsTo = [documentoProveedor: DocumentoProveedor]

    static constraints = {
    }
}
