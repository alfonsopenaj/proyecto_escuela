package org.bedu.proyecto_escuela.mapper;

import org.bedu.proyecto_escuela.dto.AlumnoDTO;
import org.bedu.proyecto_escuela.dto.CreateAlumnoDTO;
import org.bedu.proyecto_escuela.dto.UpdateAlumnoDTO;
import org.bedu.proyecto_escuela.model.Alumno;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AlumnoMapper {

    AlumnoDTO toDTO(Alumno model);

    List<AlumnoDTO> toDTO(List<Alumno> model);

    @Mapping(target = "id_alumno", ignore = true)
    Alumno toModel(CreateAlumnoDTO dto);

    @Mapping(target = "id_alumno", ignore = true)
    void update(@MappingTarget Alumno alumno, UpdateAlumnoDTO data);
}