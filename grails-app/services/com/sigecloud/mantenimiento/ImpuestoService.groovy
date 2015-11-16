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

}
