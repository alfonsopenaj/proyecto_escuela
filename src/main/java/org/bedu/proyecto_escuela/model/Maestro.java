package org.bedu.proyecto_escuela.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "maestro")
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_maestro;

    @Column(nullable = false, length = 100)
    private String nombre_maestro;
}