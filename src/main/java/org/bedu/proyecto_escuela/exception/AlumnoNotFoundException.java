package org.bedu.proyecto_escuela.exception;

public class AlumnoNotFoundException extends RuntimeException {

    public AlumnoNotFoundException(long id_alumno) {
        super("ERR_DATA_NOT_FOUND", "No se encontró el alumno", id_alumno);
    }
}
