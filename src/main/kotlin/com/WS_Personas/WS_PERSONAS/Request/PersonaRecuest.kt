package com.WS_Personas.WS_PERSONAS.Request

data class PersonaRecuest (
    val nombre: String,
    val apellido_paterno: String,
    val apellido_materno: String,
    val CURP: String
){}