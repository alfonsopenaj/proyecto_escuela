package org.bedu.proyecto_escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    @Schema(description = "Código de error", example = "1222")
    private String code;

    @Schema(description = "Mensaje de error", example = "Error desconocido")
    private String message;

    @Schema(description = "Detalles del error", example = "Error desconocido")
    private Object details;
}
