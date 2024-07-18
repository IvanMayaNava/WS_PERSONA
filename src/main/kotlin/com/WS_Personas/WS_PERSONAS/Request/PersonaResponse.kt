package com.WS_Personas.WS_PERSONAS.Request

import java.time.LocalDate

data class PersonaResponse(
    val id: Long,
    val nombre: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val CURP: String,
    val fecha_registro: LocalDate,
    val fecha_modificacion: LocalDate
) {
}