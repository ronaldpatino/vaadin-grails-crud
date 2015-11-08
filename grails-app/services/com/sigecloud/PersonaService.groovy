package com.sigecloud

import com.sigecloud.modelo.Persona
import grails.transaction.Transactional

@Transactional
class PersonaService {

    List<Persona> getPersonas() {
        def personas = Persona.list(sort: "nombre" , order: "asc")
        return personas
    }

    def save(Persona persona){
        //persona.save flush:true

        if( !persona.save(flush:true) ) {
            persona.errors.each {
                println it
            }
        }
    }


    Persona find(String id){
        return Persona.get(id.toInteger())
    }

    List<Persona> search(String cadena) {

        def criteria = Persona.createCriteria()

        def personas = criteria.list {
            or {
                like ("nombre", "%${cadena}%")
                like ("nombreComercial", "%${cadena}%")
            }
            order ("nombre", "asc")
        }

        return personas
    }

}
