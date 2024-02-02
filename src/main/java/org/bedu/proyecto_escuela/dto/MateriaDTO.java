package org.bedu.proyecto_escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MateriaDTO {
    @Schema(description = "Identificador de la materia", example = "10")
    private long id_materia;

    @Schema(description = "Nombre de la materia", example = "Español")
    private String materia;

    @Schema(description = "Identificador del maestro", example = "11")
    private long id_maestro;
}
