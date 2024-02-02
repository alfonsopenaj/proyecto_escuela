package org.bedu.proyecto_escuela.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alumnomateria")
public class AlumnoMateria {

    @EmbeddedId
    private AlumnoMateriaKey id;

    @ManyToOne
    @MapsId("id_alumno")
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    @ManyToOne
    @MapsId("id_materia")
    @JoinColumn(name = "id_materia")
    private Materia materia;
}
