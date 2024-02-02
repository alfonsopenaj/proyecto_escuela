package org.bedu.proyecto_escuela.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "materia")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_materia;

    @Column(nullable = false, length = 100)
    private String materia;

    @Column(nullable = false)
    private long id_maestro;
}
