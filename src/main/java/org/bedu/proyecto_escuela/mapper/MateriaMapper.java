package org.bedu.proyecto_escuela.mapper;

import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.model.Alumno;
import org.bedu.proyecto_escuela.model.Materia;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MateriaMapper {

    MateriaDTO toDTO(Materia model);

    List<MateriaDTO> toDTO(List<Materia> model);

    @Mapping(target = "id_materia", ignore = true)
    Materia toModel(CreateMateriaDTO dto);

    @Mapping(target = "id_materia", ignore = true)
    void update(@MappingTarget Materia materia, UpdateMateriaDTO data);
}
