package com.sigecloud.mantenimiento

import com.sigecloud.modelo.UnidadMedida
import grails.transaction.Transactional

@Transactional
class UnidadMedidaService {

    List<UnidadMedida> getUnidadMedidas() {
        def unidadMedidas = UnidadMedida.list(sort: "nombre" , order: "asc")
        return unidadMedidas
    }

    UnidadMedida find(String id){
        return UnidadMedida.get(id.toInteger())
    }

    UnidadMedida find(Long id){
        return UnidadMedida.get(id)
    }


    def save(UnidadMedida unidadMedida){

        if( !unidadMedida.save(flush:true) ) {
            unidadMedida.errors.each {
                println it
            }
        }
    }

    List<UnidadMedida> search(String cadena) {

        def criteria = UnidadMedida.createCriteria()

        def unidadMedidas = criteria.list {
            or {
                like ("nombre", "%${cadena}%")
            }
            order ("nombre", "asc")
        }

        return unidadMedidas
    }
}
