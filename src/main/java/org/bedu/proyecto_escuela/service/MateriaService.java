package org.bedu.proyecto_escuela.service;

import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.exception.MaestroNotFoundException;
import org.bedu.proyecto_escuela.exception.MateriaNotFoundException;
import org.bedu.proyecto_escuela.mapper.MateriaMapper;
import org.bedu.proyecto_escuela.model.Maestro;
import org.bedu.proyecto_escuela.model.Materia;
import org.bedu.proyecto_escuela.repository.MaestroRepository;
import org.bedu.proyecto_escuela.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository repository;

    @Autowired
    private MaestroRepository maestroRepository;

    @Autowired
    private MateriaMapper mapper;

    public List<MateriaDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public MateriaDTO save(CreateMateriaDTO data) throws MaestroNotFoundException{
        Optional<Maestro> maestroExistente = maestroRepository.findById(data.getId_maestro());
        if (maestroExistente.isPresent()) {
            Materia entity = repository.save(mapper.toModel(data));

            return mapper.toDTO(entity);
        }
        else {
            throw new MaestroNotFoundException(data.getId_maestro());
        }

        /*
        Materia entity = repository.save(mapper.toModel(data));

        return mapper.toDTO(entity);
        */
    }

    public MateriaDTO update(Long id, UpdateMateriaDTO data) throws MateriaNotFoundException, MaestroNotFoundException {
        Optional<Materia> materiaExistente = repository.findById(id);
        if (materiaExistente.isPresent()) {
            Optional<Maestro> maestroExistente = maestroRepository.findById(data.getId_maestro());
            if (maestroExistente.isPresent()) {
                Materia materiaActual = materiaExistente.get();
                mapper.update(materiaActual, data);

            /*
            materiaActual.setMateria(data.getMateria());
            materiaActual.setId_maestro(data.getId_maestro());
            */
                return mapper.toDTO(repository.save(materiaActual));
            }
            else {
                throw new MaestroNotFoundException(data.getId_maestro());
            }
        }
        else {
            throw new MateriaNotFoundException(id);
        }
    }

    public void delete(Long id) throws MateriaNotFoundException{
        Optional<Materia> materiaExistente = repository.findById(id);
        if (materiaExistente.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new MateriaNotFoundException(id);
        }
    }
}