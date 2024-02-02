package org.bedu.proyecto_escuela.service;

import org.bedu.proyecto_escuela.dto.MateriaDTO;
import org.bedu.proyecto_escuela.mapper.AlumnoMateriaMapper;
import org.bedu.proyecto_escuela.mapper.MateriaMapper;
import org.bedu.proyecto_escuela.repository.AlumnoMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoMateriaService {

    @Autowired
    private AlumnoMateriaRepository repository;

    @Autowired
    private AlumnoMateriaMapper mapper;

    @Autowired
    private MateriaMapper materiaMapper;

    public void addMateria(long id_alumno, long id_materia) {
        repository.save(mapper.toModel(id_alumno, id_materia));
    }

    public List<MateriaDTO> findMateriasByAlumno(long id_alumno) {
        return materiaMapper.toDTO(repository.findMateriasByAlumno(id_alumno));
    }
}