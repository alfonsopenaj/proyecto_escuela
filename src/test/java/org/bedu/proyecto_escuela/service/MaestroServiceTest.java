package org.bedu.proyecto_escuela.service;

import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.exception.AlumnoNotFoundException;
import org.bedu.proyecto_escuela.exception.MaestroNotFoundException;
import org.bedu.proyecto_escuela.model.Alumno;
import org.bedu.proyecto_escuela.model.Maestro;
import org.bedu.proyecto_escuela.repository.MaestroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MaestroServiceTest {
    @MockBean
    private MaestroRepository repository;

    @Autowired
    private MaestroService service;

    @Test
    @DisplayName("El servicio debería ser inyectado")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("El servicios debería regresar a los maestros del reposotorio")
    void findAllTest() {
        List<Maestro> data = new LinkedList<>();

        Maestro maestro = new Maestro();

        maestro.setId_maestro(121L);
        maestro.setNombre_maestro("Carlos");

        data.add(maestro);

        when(repository.findAll()).thenReturn(data);

        List<MaestroDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(maestro.getId_maestro(), result.get(0).getId_maestro());
        assertEquals(maestro.getNombre_maestro(), result.get(0).getNombre_maestro());
    }

    @Test
    @DisplayName("El servicio debería de guardar un maestro en el repositorio")
    void saveTest() {
        CreateMaestroDTO dto = new CreateMaestroDTO();

        dto.setNombre_maestro("Mario");

        Maestro model = new Maestro();

        model.setId_maestro(321L);
        model.setNombre_maestro(dto.getNombre_maestro());

        when(repository.save(any(Maestro.class))).thenReturn(model);

        MaestroDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId_maestro(), result.getId_maestro());
        assertEquals(model.getNombre_maestro(), result.getNombre_maestro());
    }

    @Test
    @DisplayName("El servicio debería llamar un error si el maestro no fue encontrado")
    void updateWithErrorTest() {
        UpdateMaestroDTO dto = new UpdateMaestroDTO();
        Optional<Maestro> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MaestroNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("El servicio debería actualizar un maestro en el repositorio")
    void updateTest() throws MaestroNotFoundException {
        UpdateMaestroDTO dto = new UpdateMaestroDTO();

        dto.setNombre_maestro("Daniel");

        Maestro maestro = new Maestro();

        maestro.setId_maestro(321L);
        maestro.setNombre_maestro("Roberto");

        when(repository.findById(anyLong())).thenReturn(Optional.of(maestro));

        service.update(321L, dto);

        assertEquals(dto.getNombre_maestro(), maestro.getNombre_maestro());
        verify(repository, times(1)).save(maestro);
    }

    @Test
    @DisplayName("El servicio debería llamar un error si el maestro no fue encontrado")
    void deleteWithErrorTest() {
        Optional<Maestro> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MaestroNotFoundException.class, () -> service.delete(345L));
    }

    @Test
    @DisplayName("El servicio debe borrar un maestro por el id en el repositorio")
    void deleteByIdTest() throws MaestroNotFoundException {
        Maestro maestro = new Maestro();

        maestro.setId_maestro(321L);
        maestro.setNombre_maestro("Maria");

        when(repository.findById(anyLong())).thenReturn(Optional.of(maestro));

        service.delete(321L);

        verify(repository, times(1)).deleteById(321L);
    }
}