package org.bedu.proyecto_escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MaestroDTO {
    @Schema(description = "Identificador del maestro", example = "10")
    private long id_maestro;

    @Schema(description = "Nombre del maestro", example = "Pedro Pérez Pérez")
    private String nombre_maestro;
}
