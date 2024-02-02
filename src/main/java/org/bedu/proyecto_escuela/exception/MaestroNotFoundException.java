package org.bedu.proyecto_escuela.exception;

public class MaestroNotFoundException extends RuntimeException {

    public MaestroNotFoundException(long id_maestro) {
        super("ERR_DATA_NOT_FOUND", "No se encontró el maestro", id_maestro);
    }
}