package com.sigecloud.mantenimiento

import com.sigecloud.modelo.Producto
import grails.transaction.Transactional

@Transactional
class ProductoService {

    List<Producto> getProductos() {
        def impuestos = Producto.list(sort: "nombre" , order: "asc")
        return impuestos
    }

    Producto find(String id){
        return Producto.get(id.toInteger())
    }


    def save(Producto impuesto){

        if( !impuesto.save(flush:true) ) {
            impuesto.errors.each {
                println it
            }
        }
    }

    List<Producto> search(String cadena) {

        def criteria = Producto.createCriteria()

        def impuestos = criteria.list {
            or {
                like ("nombre", "%${cadena}%")
            }
            order ("nombre", "asc")
        }

        return impuestos
    }

}
