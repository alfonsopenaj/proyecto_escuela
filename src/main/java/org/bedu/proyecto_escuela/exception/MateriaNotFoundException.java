package org.bedu.proyecto_escuela.exception;

public class MateriaNotFoundException extends RuntimeException {

    public MateriaNotFoundException(long id_materia) {
        super("ERR_DATA_NOT_FOUND", "No se encontró la materia", id_materia);
    }
}
