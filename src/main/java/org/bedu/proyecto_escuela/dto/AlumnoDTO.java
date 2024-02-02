package org.bedu.proyecto_escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlumnoDTO {
    @Schema(description = "Identificador del alumno", example = "20")
    private long id_alumno;

    @Schema(description = "Matrícula del alumno", example = "1222")
    private int matricula;

    @Schema(description = "Nombre del alumno", example = "Juan López López")
    private String nombre_alumno;

    @Schema(description = "Sexo del alumno", example = "M")
    private String sexo;

    @Schema(description = "Número telefónico del alumno", example = "9616170000")
    private String telefono;

    @Schema(description = "Dirección del alumno", example = "Dirección conocida")
    private String direccion;

    @Schema(description = "Correo electrónico del alumno", example = "nombre@servidor.com")
    private String email;
}
