package org.bedu.proyecto_escuela.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.exception.AlumnoNotFoundException;
import org.bedu.proyecto_escuela.service.AlumnoMateriaService;
import org.bedu.proyecto_escuela.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService service;

    @Autowired
    private AlumnoMateriaService alumnoMateriaService;

    @Operation(summary = "Obtiene la lista de todos los alumnos")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    public List<AlumnoDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea un nuevo alumno")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlumnoDTO save(@Valid @RequestBody CreateAlumnoDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Asocia una materia a un alumno")
    @PostMapping("{id}/materias")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMateria(@PathVariable long id, @RequestBody AddMateriaDTO data) {
        alumnoMateriaService.addMateria(id, data.getId_materia());
    }

    @Operation(summary = "Obtiene las materias de un alumno determinado")
    @GetMapping("{id}/materias")
    @ResponseStatus(HttpStatus.OK)
    public List<MateriaDTO> findMateriasByAlumno(@PathVariable long id) {
        return alumnoMateriaService.findMateriasByAlumno(id);
    }

    @Operation(summary = "Actualiza la información de un alumno")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlumnoDTO update(@PathVariable("id") Long id,@Valid @RequestBody UpdateAlumnoDTO data) throws AlumnoNotFoundException {
        return service.update(id, data);
    }

    @Operation(summary = "Elimina un alumno")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) throws AlumnoNotFoundException{
        service.delete(id);
    }
}