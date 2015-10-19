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
        persona.save flush:true
    }


    Persona find(String id){
        return Persona.get(id.toInteger())
    }
}
