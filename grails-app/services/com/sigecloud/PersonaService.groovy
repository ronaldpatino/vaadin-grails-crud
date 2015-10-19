package com.sigecloud

import com.sigecloud.modelo.Persona
import grails.transaction.Transactional

@Transactional
class PersonaService {

    List<Persona> getPersonas() {
        return Persona.list()
    }

    def save(Persona persona){
        persona.save flush:true
    }


    Persona find(String id){
        return Persona.get(id.toInteger())
    }
}
