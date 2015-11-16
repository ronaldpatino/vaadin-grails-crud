package com.sigecloud.mantenimiento

import com.sigecloud.modelo.Impuesto
import grails.transaction.Transactional

@Transactional
class ImpuestoService {

    List<Impuesto> getImpuestos() {
        def impuestos = Impuesto.list(sort: "nombre" , order: "asc")
        return impuestos
    }

    Impuesto find(String id){
        return Impuesto.get(id.toInteger())
    }


    def save(Impuesto impuesto){

        if( !impuesto.save(flush:true) ) {
            impuesto.errors.each {
                println it
            }
        }
    }

    List<Impuesto> search(String cadena) {

        def criteria = Impuesto.createCriteria()

        def impuestos = criteria.list {
            or {
                like ("nombre", "%${cadena}%")
            }
            order ("nombre", "asc")
        }

        return impuestos
    }

}
