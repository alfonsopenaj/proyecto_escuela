package org.bedu.proyecto_escuela.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.exception.MaestroNotFoundException;
import org.bedu.proyecto_escuela.exception.MateriaNotFoundException;
import org.bedu.proyecto_escuela.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    private MateriaService service;

    @Operation(summary = "Obtiene la lista de todas las materias")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MateriaDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea un nueva materia")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MateriaDTO save(@Valid @RequestBody CreateMateriaDTO data) throws MaestroNotFoundException{
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de una materia")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MateriaDTO update(@PathVariable("id") Long id,@Valid @RequestBody UpdateMateriaDTO data) throws MateriaNotFoundException, MaestroNotFoundException {
        return service.update(id, data);
    }

    @Operation(summary = "Elimina una materia")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id)throws MateriaNotFoundException {
        service.delete(id);
    }
}