package org.bedu.proyecto_escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddMateriaDTO {

    @Schema(description = "Identificador de la materia a asociar", example = "10")
    private long id_materia;
}
