package org.bedu.proyecto_escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateMateriaDTO {

    @Schema(description = "Nombre de la materia", example = "Español")
    @NotBlank
    private String materia;

    @Schema(description = "Identificador del maestro", example = "10")
    @Min(1)
    private long id_maestro;
}
