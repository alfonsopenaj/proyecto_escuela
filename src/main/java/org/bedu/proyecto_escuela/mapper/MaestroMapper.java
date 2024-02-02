package org.bedu.proyecto_escuela.mapper;

import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.model.Alumno;
import org.bedu.proyecto_escuela.model.Maestro;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MaestroMapper {

    MaestroDTO toDTO(Maestro model);

    List<MaestroDTO> toDTO(List<Maestro> model);

    @Mapping(target = "id_maestro", ignore = true)
    Maestro toModel(CreateMaestroDTO dto);

    @Mapping(target = "id_maestro", ignore = true)
    void update(@MappingTarget Maestro maestro, UpdateMaestroDTO data);
}
