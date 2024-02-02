package org.bedu.proyecto_escuela.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AlumnoMateriaKey {

    @Column(name = "id_alumno")
    private long id_alumno;

    @Column(name = "id_materia")
    private long id_materia;
}
