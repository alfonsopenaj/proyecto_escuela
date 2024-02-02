package org.bedu.proyecto_escuela.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.exception.AlumnoNotFoundException;
import org.bedu.proyecto_escuela.exception.MaestroNotFoundException;
import org.bedu.proyecto_escuela.service.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("maestro")
public class MaestroController {

    @Autowired
    private MaestroService service;

    @Operation(summary = "Obtiene la lista de todos los maestros")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MaestroDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea un nuevo maestro")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaestroDTO save(@Valid @RequestBody CreateMaestroDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de un maestro")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaestroDTO update(@PathVariable("id") Long id, @Valid @RequestBody UpdateMaestroDTO data) throws MaestroNotFoundException {
        return service.update(id, data);
    }

    @Operation(summary = "Elimina un maestro")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) throws MaestroNotFoundException{
        service.delete(id);
    }
}
