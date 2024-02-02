package org.bedu.proyecto_escuela.model;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_alumno;

    @Column(unique = true)
    @Range(min = 1, max = 1000)
    private int matricula;

    @Column(nullable = false, length = 100)
    private String nombre_alumno;

    @Column(nullable = false, length = 1)
    private String sexo;

    @Column(nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String email;
}