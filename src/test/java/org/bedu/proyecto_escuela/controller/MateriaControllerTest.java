package org.bedu.proyecto_escuela.controller;

import org.bedu.proyecto_escuela.dto.MateriaDTO;
import org.bedu.proyecto_escuela.dto.UpdateMateriaDTO;
import org.bedu.proyecto_escuela.exception.MaestroNotFoundException;
import org.bedu.proyecto_escuela.exception.MateriaNotFoundException;
import org.bedu.proyecto_escuela.service.MateriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MateriaControllerTest {
    @MockBean
    private MateriaService service;

    @Autowired
    private MateriaController controller;

    @Test
    @DisplayName("Controlador deberia estar inyectado")
    public void smokeTest() {
        //Assert
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controlador deberia regresar una lista de materias")
    public void findAllTest(){
        //Arrange
        List<MateriaDTO> DatosFalsos = new LinkedList<>();
        MateriaDTO MateriaFalso = new MateriaDTO();
        MateriaFalso.setId_materia(100);
        MateriaFalso.setMateria("Biologia");
        MateriaFalso.setId_maestro(5);

        DatosFalsos.add(MateriaFalso);
        when(service.findAll()).thenReturn(DatosFalsos);

        //Act
        List<MateriaDTO> result = controller.findAll();

        //Assert
        assertEquals(DatosFalsos, result);
    }

    @Test
    @DisplayName("El Controlador deberia actualizar los datos de la materia")
    public void updateTest() throws MateriaNotFoundException, MaestroNotFoundException {
        UpdateMateriaDTO dto = new UpdateMateriaDTO();

        dto.setMateria("Quimica");
        dto.setId_maestro(8);

        MateriaDTO saved = new MateriaDTO();

        saved.setId_materia(110);
        saved.setMateria("Quimica");
        saved.setId_maestro(8);

        when(service.update(110L, dto)).thenReturn(saved);

        // Act
        MateriaDTO result = controller.update(110L,dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getMateria(), result.getMateria());
        assertEquals(dto.getId_maestro(), result.getId_maestro());
    }

    @Test
    @DisplayName("El Controlador deberia eliminar la materia")
    public void deleteTest() throws MateriaNotFoundException {
        // Pendiente por preguntar
    }
}