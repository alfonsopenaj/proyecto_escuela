package org.bedu.proyecto_escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAlumnoDTO {

    @Schema(description = "Matrícula del alumno", example = "1222")
    @Min(1)
    @Max(1000)
    private int matricula;

    @Schema(description = "Nombre del alumno", example = "Juan López López")
    @NotBlank
    private String nombre_alumno;

    @Schema(description = "Sexo del alumno", example = "M")
    @NotBlank
    private String sexo;

    @Schema(description = "Número telefónico del alumno", example = "9616170000")
    @NotBlank
    private String telefono;

    @Schema(description = "Dirección del alumno", example = "Dirección conocida")
    @NotBlank
    private String direccion;

    @Schema(description = "Correo electrónico del alumno", example = "nombre@servidor.com")
    @NotBlank
    @Email
    private String email;
}