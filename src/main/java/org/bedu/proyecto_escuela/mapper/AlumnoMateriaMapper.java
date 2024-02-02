package org.bedu.proyecto_escuela.mapper;

import org.bedu.proyecto_escuela.model.AlumnoMateria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AlumnoMateriaMapper {

    @Mapping(source = "id_alumno", target = "id.id_alumno")
    @Mapping(source = "id_alumno", target = "alumno.id_alumno")
    @Mapping(source = "id_materia", target = "id.id_materia")
    @Mapping(source = "id_materia", target = "materia.id_materia")
    AlumnoMateria toModel(Long id_alumno, Long id_materia);
}
