package com.WS_Personas.WS_PERSONAS.Service
import com.WS_Personas.WS_PERSONAS.entity.PERSONAS
import com.WS_Personas.WS_PERSONAS.repository.PersonaRepository
import com.WS_Personas.WS_PERSONAS.Request.PersonaRecuest
import com.WS_Personas.WS_PERSONAS.Request.PersonaResponse
import com.WS_Personas.WS_PERSONAS.Request.PersonasList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class PersonaService @Autowired constructor(private val personaRepository: PersonaRepository){
    fun crearPersona(personaRequest: PersonaRecuest): PERSONAS {

        val existingpersona = personaRepository.findByCURP(personaRequest.CURP)
        if(existingpersona != null){
            throw IllegalArgumentException("El CURP ya se encuentra registrado")
        }

        val persona = PERSONAS(
            nombre = personaRequest.nombre,
            apellido_paterno = personaRequest.apellido_paterno,
            apellido_materno = personaRequest.apellido_materno,
            CURP=personaRequest.CURP,
        )
        return personaRepository.save(persona)
    }

    fun getPersonaByCURP(CURP: String): PersonaResponse {
        val personas = personaRepository.findByCURP(CURP) ?: throw RuntimeException("No hay datos")
        return PersonaResponse(
            id = personas.id,
            nombre = personas.nombre,
            apellido_paterno = personas.apellido_paterno,
            apellido_materno = personas.apellido_materno,
            CURP = personas.CURP,
            fecha_registro = personas.fecha_registro,
            fecha_modificacion = personas.fecha_modificacion
        )
    }

    fun getAllPersonas(): List<PersonasList> {
        val personas = personaRepository.findAll()
        return personas.map { persona -> PersonasList(
            id = persona.id,
            nombre = persona.nombre,
            CURP = persona.CURP)
        }
    }
    fun getpersonaById(id: Long): PERSONAS {
        return personaRepository.findById(id).orElseThrow{ RuntimeException("No hay datos")}
    }

    fun ActualizarPersonas(id: Long, personaRequest: PersonaRecuest): PERSONAS {
        val personaExistente = getpersonaById(id)

        val personaAcutalizada = personaExistente.copy(
            nombre = personaRequest.nombre,
            apellido_paterno = personaRequest.apellido_paterno,
            apellido_materno = personaRequest.apellido_materno,
            CURP = personaRequest.CURP,
            fecha_registro = personaExistente.fecha_registro,
            fecha_modificacion = LocalDate.now()
        )
        return personaRepository.save(personaAcutalizada)
    }
    fun eliminarPersona(CURP: String){
    val personnasExistente = personaRepository.findByCURP(CURP)?: throw RuntimeException("No hay datos")
        personaRepository.deleteById(personnasExistente.id)
    }
}