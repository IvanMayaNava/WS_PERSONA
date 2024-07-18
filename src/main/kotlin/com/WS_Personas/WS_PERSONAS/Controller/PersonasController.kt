package com.WS_Personas.WS_PERSONAS.Controller
import com.WS_Personas.WS_PERSONAS.Request.PersonaRecuest
import com.WS_Personas.WS_PERSONAS.Request.PersonaResponse
import com.WS_Personas.WS_PERSONAS.Request.PersonasList
import com.WS_Personas.WS_PERSONAS.Service.PersonaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag


@RestController
@RequestMapping("/persona")
@Tag(name = "Persona", description = "This API")

class PersonasController(private val personaService: PersonaService){

    @PostMapping
    @Operation(summary = "Crea una nueva persona")
    fun registrarPersona(@RequestBody personaRecuest: PersonaRecuest): ResponseEntity<Any> {
        return try {
            val persona = personaService.crearPersona(personaRecuest)
            ResponseEntity.ok(persona)
        }
        catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)
        }
    }

    @GetMapping("/personas")
    @Operation(summary = "Lista de personas")
    fun getAllPersonas(): ResponseEntity<List<PersonasList>> {
        val personas = personaService.getAllPersonas()
        return ResponseEntity.ok(personas)
    }

    @GetMapping("/buscar/{CURP}")
    @Operation(summary = "Buscar a la persona por medio del CURP")
    fun getPersonaByCURP(@PathVariable CURP: String): ResponseEntity<PersonaResponse> {
        return try {
            val personas = personaService.getPersonaByCURP(CURP)
            ResponseEntity.ok(personas)
        } catch (e: RuntimeException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Se actualizan los datos de la persona")
    fun ActualizarPersonas(@PathVariable id: Long, @RequestBody informacionResponse: PersonaRecuest): ResponseEntity<Any> {
        return try {

            val personaActualizada = personaService.ActualizarPersonas(id, informacionResponse)
            ResponseEntity.ok(personaActualizada)
        }catch (e: RuntimeException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @DeleteMapping("/eliminar/{CURP}")
    @Operation(summary = "Se eliminar la persona")
    fun eliminarPersona(@PathVariable CURP: String): ResponseEntity<String> {
        return try {
            personaService.eliminarPersona(CURP)
            ResponseEntity.ok("Persona Eliminada")
        }catch (e: RuntimeException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada")
        }
    }
}