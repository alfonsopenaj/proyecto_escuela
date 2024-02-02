package org.bedu.proyecto_escuela.controller;

import jakarta.validation.Valid;
import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.exception.AlumnoNotFoundException;
import org.bedu.proyecto_escuela.service.AlumnoMateriaService;
import org.bedu.proyecto_escuela.service.AlumnoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AlumnoControllerTest {
    @MockBean
    private AlumnoService service;
    @MockBean
    private AlumnoMateriaService alumnoMateriaService;
    @Autowired
    private AlumnoController controller;

    @Test
    @DisplayName("Controlador deberia estar inyectado")
    public void smokeTest() {
        //Assert
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controlador deberia regresar una lista")
    public void findAllTest(){
        //Arrange
        List<AlumnoDTO> DatosFalsos = new LinkedList<>();
        AlumnoDTO AlumnoFalso = new AlumnoDTO();
        AlumnoFalso.setId_alumno(100);
        AlumnoFalso.setMatricula(100);
        AlumnoFalso.setNombre_alumno("Francisco");
        AlumnoFalso.setSexo("M");
        AlumnoFalso.setTelefono("4444333231");
        AlumnoFalso.setDireccion("Robles 333");
        AlumnoFalso.setEmail("fracisco1@gmail.com");

        DatosFalsos.add(AlumnoFalso);
        when(service.findAll()).thenReturn(DatosFalsos);

        //Act
        List<AlumnoDTO> result = controller.findAll();

        //Assert
        assertEquals(DatosFalsos, result);
    }

    @Test
    @DisplayName("El Controlador deberia guardar un alumno")
    public void saveTest() {
        // Arrange
        CreateAlumnoDTO dto = new CreateAlumnoDTO();

        dto.setMatricula(100);
        dto.setNombre_alumno("Francisco");
        dto.setSexo("M");
        dto.setTelefono("4444333231");
        dto.setDireccion("Robles 333");
        dto.setEmail("fracisco1@gmail.com");

        AlumnoDTO saved = new AlumnoDTO();

        saved.setId_alumno(100);
        saved.setMatricula(100);
        saved.setNombre_alumno("Francisco");
        saved.setSexo("M");
        saved.setTelefono("4444333231");
        saved.setDireccion("Robles 333");
        saved.setEmail("fracisco1@gmail.com");

        when(service.save(any(CreateAlumnoDTO.class))).thenReturn(saved);

        // Act
        AlumnoDTO result = controller.save(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getMatricula(), result.getMatricula());
        assertEquals(dto.getNombre_alumno(), result.getNombre_alumno());
        assertEquals(dto.getSexo(), result.getSexo());
        assertEquals(dto.getTelefono(), result.getTelefono());
        assertEquals(dto.getDireccion(), result.getDireccion());
        assertEquals(dto.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("El Controlador deberia asociar una materia a un alumno")
    public void addMateriaTest() {
        //Pendiente por preguntar
    }

    @Test
    @DisplayName("El Controlador deberia regresar una lista de las materias asociadas con el alumno")
    public void findMateriasByAlumnoTest() {
        //Arrange
        List<MateriaDTO> DatosFalsos = new LinkedList<>();
        MateriaDTO MateriaFalso = new MateriaDTO();
        MateriaFalso.setId_materia(5);
        MateriaFalso.setMateria("Biologia");
        MateriaFalso.setId_maestro(3);

        DatosFalsos.add(MateriaFalso);
        when(alumnoMateriaService.findMateriasByAlumno(1)).thenReturn(DatosFalsos);

        //Act
        List<MateriaDTO> result = controller.findMateriasByAlumno(1);

        //Assert
        assertEquals(DatosFalsos, result);
    }

    @Test
    @DisplayName("El Controlador deberia actualizar los datos del alumno")
    public void updateTest() throws AlumnoNotFoundException {
        UpdateAlumnoDTO dto = new UpdateAlumnoDTO();

        dto.setMatricula(100);
        dto.setNombre_alumno("Francisco");
        dto.setSexo("M");
        dto.setTelefono("4444333231");
        dto.setDireccion("Robles 333");
        dto.setEmail("fracisco1@gmail.com");

        AlumnoDTO saved = new AlumnoDTO();

        saved.setId_alumno(100);
        saved.setMatricula(100);
        saved.setNombre_alumno("Francisco");
        saved.setSexo("M");
        saved.setTelefono("4444333231");
        saved.setDireccion("Robles 333");
        saved.setEmail("fracisco1@gmail.com");

        when(service.update(1L, dto)).thenReturn(saved);

        // Act
        AlumnoDTO result = controller.update(1L,dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getMatricula(), result.getMatricula());
        assertEquals(dto.getNombre_alumno(), result.getNombre_alumno());
        assertEquals(dto.getSexo(), result.getSexo());
        assertEquals(dto.getTelefono(), result.getTelefono());
        assertEquals(dto.getDireccion(), result.getDireccion());
        assertEquals(dto.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("El Controlador deberia eliminar el alumno")
    public void deleteTest() throws AlumnoNotFoundException {
        // Pendiente por preguntar
    }
}