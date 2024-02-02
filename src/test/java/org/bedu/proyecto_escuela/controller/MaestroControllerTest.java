package org.bedu.proyecto_escuela.controller;
import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.exception.MaestroNotFoundException;

import org.bedu.proyecto_escuela.service.MaestroService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MaestroControllerTest {

    @MockBean
    private MaestroService service;

    @Autowired
    private MaestroController controller;

    @Test
    @DisplayName("Controlador deberia estar inyectado")
    public void smokeTest() {
        //Assert
        assertNotNull(controller);
    }
    @Test
    @DisplayName("Controlador deberia regresar una lista")
    public void findAllTest() {
        //Arrange
        List<MaestroDTO> DatosFalsos = new LinkedList<>();
        MaestroDTO MaestroFalso = new MaestroDTO();
        MaestroFalso.setId_maestro(100);
        MaestroFalso.setNombre_maestro("Javier");

        DatosFalsos.add(MaestroFalso);
        when(service.findAll()).thenReturn(DatosFalsos);

        //Act
        List<MaestroDTO> result = controller.findAll();

        //Assert
        assertEquals(DatosFalsos, result);

    }

        @Test
        @DisplayName("El Controlador deberia guardar un maestro")
        public void saveTest() {
            // Arrange
            CreateMaestroDTO dto = new CreateMaestroDTO();

            dto.setNombre_maestro("Javier");

            MaestroDTO saved = new MaestroDTO();

            saved.setNombre_maestro("Javier");

            when(service.save(any(CreateMaestroDTO.class))).thenReturn(saved);

            // Act
            MaestroDTO result = controller.save(dto);

            // Assert
            assertNotNull(result);
            assertEquals(dto.getNombre_maestro(), result.getNombre_maestro());
        }
    @Test
    @DisplayName("El Controlador deberia actualizar los datos del maestro")
    public void updateTest() throws MaestroNotFoundException {
        UpdateMaestroDTO dto = new UpdateMaestroDTO();

        dto.setNombre_maestro("Richard");


        MaestroDTO saved = new MaestroDTO();

        saved.setNombre_maestro("Richard");


        when(service.update(1L, dto)).thenReturn(saved);

        // Act
        MaestroDTO result = controller.update(1L,dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getNombre_maestro(), result.getNombre_maestro());

    }

    @Test
    @DisplayName("El Controlador deberia eliminar un maestro")
    public void deleteTest() throws MaestroNotFoundException{
        controller.delete(111L);

        verify(service, times(1)).delete(111L);
    }












}