package org.bedu.proyecto_escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateMaestroDTO {
    @Schema(description = "Nombre del maestro", example = "Pedro Pérez Pérez")
    @NotBlank
    private String nombre_maestro;
}
