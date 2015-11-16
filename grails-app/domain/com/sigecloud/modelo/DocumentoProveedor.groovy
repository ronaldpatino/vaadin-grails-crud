package com.sigecloud.modelo

class DocumentoProveedor {

    Date fecha
    Date fechaVencimiento

    String numeroSecuencial
    String establecimiento
    String puntoEmision
    String observacion

    BigDecimal subTotal
    BigDecimal total
    BigDecimal descuento
    BigDecimal porcentajeDescuento
    BigDecimal subTotalExcento
    BigDecimal subtotalImpuesto
    BigDecimal salorImpuestoIva

    Boolean bloqueado = false
    Boolean anulado = false
    Boolean retencionAsumida = false
    Boolean validado = false
    Boolean pagado = false


    static hasMany = [documentoProveedorDetalles: DocumentoProveedorDetalle]

    static constraints = {
    }


}
