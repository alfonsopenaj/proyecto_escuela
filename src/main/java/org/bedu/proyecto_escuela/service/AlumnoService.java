package org.bedu.proyecto_escuela.service;

import org.bedu.proyecto_escuela.dto.AlumnoDTO;
import org.bedu.proyecto_escuela.dto.CreateAlumnoDTO;
import org.bedu.proyecto_escuela.dto.UpdateAlumnoDTO;
import org.bedu.proyecto_escuela.exception.AlumnoNotFoundException;
import org.bedu.proyecto_escuela.mapper.AlumnoMapper;
import org.bedu.proyecto_escuela.model.Alumno;
import org.bedu.proyecto_escuela.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository repository;

    @Autowired
    private AlumnoMapper mapper;

    public List<AlumnoDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public AlumnoDTO save(CreateAlumnoDTO data) {
        Alumno entity = repository.save(mapper.toModel(data));

        return mapper.toDTO(entity);
    }

    public AlumnoDTO update(Long id, UpdateAlumnoDTO data) throws AlumnoNotFoundException{
        Optional<Alumno> alumnoExistente = repository.findById(id);
        if (alumnoExistente.isPresent()) {
            Alumno alumnoActual = alumnoExistente.get();
            /*
            alumnoActual.setMatricula(data.getMatricula());
            alumnoActual.setNombre_alumno(data.getNombre_alumno());
            alumnoActual.setSexo(data.getSexo());
            alumnoActual.setTelefono(data.getTelefono());
            alumnoActual.setDireccion(data.getDireccion());
            alumnoActual.setEmail(data.getEmail());
            */
            mapper.update(alumnoActual, data);
            return mapper.toDTO(repository.save(alumnoActual));
        }
        else {
            throw new AlumnoNotFoundException(id);
        }
    }

    public void delete(Long id) throws AlumnoNotFoundException{
        Optional<Alumno> alumnoExistente = repository.findById(id);

        if (alumnoExistente.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new AlumnoNotFoundException(id);
        }

        //repository.deleteById(id);
    }
}