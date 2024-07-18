package com.WS_Personas.WS_PERSONAS.repository;
import com.WS_Personas.WS_PERSONAS.entity.PERSONAS
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
interface PersonaRepository : JpaRepository<PERSONAS, Long>{
    fun findByCURP(CURP: String): PERSONAS?
}

class PERSONASREPOSITORY {


}