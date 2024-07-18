package com.WS_Personas.WS_PERSONAS.entity;
import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "persona")

data class PERSONAS(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    val id: Long = 0,

    @Column(name = "nombre")
    val nombre:String = "",

    @Column(name = "apellido_paterno")
    val apellido_paterno:String = "",

    @Column(name = "apellido_materno")
    val apellido_materno:String = "",

    @Column(name = "fecha_registro")
    var fecha_registro: LocalDate = LocalDate.now(),

    @Column(name = "CURP")
    val CURP:String = "",

    @Column(name = "fecha_modificacion")
    var fecha_modificacion: LocalDate = LocalDate.now(),
)
{
    @PreUpdate
    fun beforeUpdate(){
        fecha_modificacion = LocalDate.now()
    }
}
