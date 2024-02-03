package org.bedu.proyecto_escuela.service;

import org.bedu.proyecto_escuela.dto.AlumnoDTO;
import org.bedu.proyecto_escuela.dto.CreateAlumnoDTO;
import org.bedu.proyecto_escuela.dto.UpdateAlumnoDTO;
import org.bedu.proyecto_escuela.exception.AlumnoNotFoundException;
import org.bedu.proyecto_escuela.model.Alumno;
import org.bedu.proyecto_escuela.repository.AlumnoRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AlumnoServiceTest {
    @MockBean
    private AlumnoRepository repository;

    @Autowired
    private AlumnoService service;

    @Test
    @DisplayName("El servicio debería ser inyectado")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("El servicio debería regresar los alumnos del reposotorio")
    void findAllTest() {
        List<Alumno> data = new LinkedList<>();

        Alumno alumno = new Alumno();

        alumno.setId_alumno(121L);
        alumno.setMatricula(100);
        alumno.setNombre_alumno("Francisco");
        alumno.setSexo("M");
        alumno.setTelefono("4444333231");
        alumno.setDireccion("Robles 333");
        alumno.setEmail("fracisco1@gmail.com");

        data.add(alumno);

        when(repository.findAll()).thenReturn(data);

        List<AlumnoDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(alumno.getId_alumno(), result.get(0).getId_alumno());
        assertEquals(alumno.getMatricula(), result.get(0).getMatricula());
        assertEquals(alumno.getNombre_alumno(), result.get(0).getNombre_alumno());
        assertEquals(alumno.getSexo(), result.get(0).getSexo());
        assertEquals(alumno.getTelefono(), result.get(0).getTelefono());
        assertEquals(alumno.getDireccion(), result.get(0).getDireccion());
        assertEquals(alumno.getEmail(), result.get(0).getEmail());
    }

    @Test
    @DisplayName("El servicio debería de guardar un alumno en el repositorio")
    void saveTest() {
        CreateAlumnoDTO dto = new CreateAlumnoDTO();

        dto.setMatricula(100);
        dto.setNombre_alumno("Francisco");
        dto.setSexo("M");
        dto.setTelefono("4444333231");
        dto.setDireccion("Robles 333");
        dto.setEmail("fracisco1@gmail.com");

        Alumno model = new Alumno();

        model.setId_alumno(321L);
        model.setMatricula(dto.getMatricula());
        model.setNombre_alumno(dto.getNombre_alumno());
        model.setSexo(dto.getSexo());
        model.setTelefono(dto.getTelefono());
        model.setDireccion(dto.getDireccion());
        model.setEmail(dto.getEmail());

        when(repository.save(any(Alumno.class))).thenReturn(model);

        AlumnoDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId_alumno(), result.getId_alumno());
        assertEquals(model.getMatricula(), result.getMatricula());
        assertEquals(model.getNombre_alumno(), result.getNombre_alumno());
        assertEquals(model.getSexo(), result.getSexo());
        assertEquals(model.getTelefono(), result.getTelefono());
        assertEquals(model.getDireccion(), result.getDireccion());
        assertEquals(model.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("El servicio debería llamar un error si el alumno no fue encontrado")
    void updateWithErrorTest() {
        UpdateAlumnoDTO dto = new UpdateAlumnoDTO();
        Optional<Alumno> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(AlumnoNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("El servicio debería actualizar un alumno en el repositorio")
    void updateTest() throws AlumnoNotFoundException {
        UpdateAlumnoDTO dto = new UpdateAlumnoDTO();

        dto.setMatricula(100);
        dto.setNombre_alumno("Francisco");
        dto.setSexo("M");
        dto.setTelefono("4444333231");
        dto.setDireccion("Robles 333");
        dto.setEmail("fracisco1@gmail.com");

        Alumno alumno = new Alumno();

        alumno.setId_alumno(321L);
        alumno.setMatricula(111);
        alumno.setNombre_alumno("Roberto");
        alumno.setSexo("M");
        alumno.setTelefono("9616179700");
        alumno.setDireccion("Fidel Velazquez");
        alumno.setEmail("roberto@gmail.com");

        when(repository.findById(anyLong())).thenReturn(Optional.of(alumno));

        service.update(321L, dto);

        assertEquals(dto.getMatricula(), alumno.getMatricula());
        assertEquals(dto.getNombre_alumno(), alumno.getNombre_alumno());
        assertEquals(dto.getSexo(), alumno.getSexo());
        assertEquals(dto.getTelefono(), alumno.getTelefono());
        assertEquals(dto.getDireccion(), alumno.getDireccion());
        assertEquals(dto.getEmail(), alumno.getEmail());
        verify(repository, times(1)).save(alumno);
    }

    @Test
    @DisplayName("El servicio debería llamar un error si el alumno no fue encontrado")
    void deleteWithErrorTest() {
        Optional<Alumno> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(AlumnoNotFoundException.class, () -> service.delete(345L));
    }

    @Test
    @DisplayName("El servicio debe borrar un alumno por el id en el repositorio")
    void deleteByIdTest() throws AlumnoNotFoundException {
        Alumno alumno = new Alumno();

        alumno.setId_alumno(321L);
        alumno.setMatricula(111);
        alumno.setNombre_alumno("Roberto");
        alumno.setSexo("M");
        alumno.setTelefono("9616179700");
        alumno.setDireccion("Fidel Velazquez");
        alumno.setEmail("roberto@gmail.com");

        when(repository.findById(anyLong())).thenReturn(Optional.of(alumno));

        service.delete(321L);

        verify(repository, times(1)).deleteById(321L);
    }
}