package org.bedu.proyecto_escuela.service;

import org.bedu.proyecto_escuela.dto.*;
import org.bedu.proyecto_escuela.exception.AlumnoNotFoundException;
import org.bedu.proyecto_escuela.exception.MaestroNotFoundException;
import org.bedu.proyecto_escuela.exception.MateriaNotFoundException;
import org.bedu.proyecto_escuela.model.Alumno;
import org.bedu.proyecto_escuela.model.Maestro;
import org.bedu.proyecto_escuela.model.Materia;
import org.bedu.proyecto_escuela.repository.MaestroRepository;
import org.bedu.proyecto_escuela.repository.MateriaRepository;
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
class MateriaServiceTest {
    @MockBean
    private MateriaRepository repository;

    @MockBean
    private MaestroRepository maestroRepository;

    @Autowired
    private MateriaService service;

    @Test
    @DisplayName("El servicio debería ser inyectado")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("El servicio debería regresar las materias del reposotorio")
    void findAllTest() {
        List<Materia> data = new LinkedList<>();

        Materia materia = new Materia();

        materia.setId_materia(121L);
        materia.setMateria("Historia");
        materia.setId_maestro(321L);

        data.add(materia);

        when(repository.findAll()).thenReturn(data);

        List<MateriaDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(materia.getId_materia() , result.get(0).getId_materia());
        assertEquals(materia.getMateria(), result.get(0).getMateria());
        assertEquals(materia.getId_maestro(), result.get(0).getId_maestro());
    }

    @Test
    @DisplayName("El servicio debería llamar un error si el maestro no fue encontrado")
    void saveWithErrorTest() {
        CreateMateriaDTO dto = new CreateMateriaDTO();
        Optional<Materia> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MaestroNotFoundException.class, () -> service.save(dto));
    }

    @Test
    @DisplayName("El servicio debería de guardar una materia en el repositorio")
    void saveTest() throws MaestroNotFoundException {
        Maestro maestro = new Maestro();

        maestro.setId_maestro(321L);
        maestro.setNombre_maestro("Maria");

        when(maestroRepository.findById(anyLong())).thenReturn(Optional.of(maestro));

        CreateMateriaDTO dto = new CreateMateriaDTO();

        dto.setMateria("Biologia");
        dto.setId_maestro(213L);

        Materia model = new Materia();

        model.setId_materia(321L);
        model.setMateria(dto.getMateria());
        model.setId_maestro(dto.getId_maestro());

        when(repository.save(any(Materia.class))).thenReturn(model);

        MateriaDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId_materia(), result.getId_materia());
        assertEquals(model.getMateria(), result.getMateria());
        assertEquals(model.getId_maestro(), result.getId_maestro());
    }

    @Test
    @DisplayName("El servicio debería llamar un error si la materia no fue encontrada")
    void updateWithErrorTest() {
        UpdateMateriaDTO dto = new UpdateMateriaDTO();
        Optional<Materia> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MateriaNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("El servicio debería actualizar una materia en el repositorio")
    void updateTest() throws MateriaNotFoundException, MaestroNotFoundException {
        Maestro maestro = new Maestro();

        maestro.setId_maestro(112L);
        maestro.setNombre_maestro("Patricia");

        when(maestroRepository.findById(anyLong())).thenReturn(Optional.of(maestro));

        UpdateMateriaDTO dto = new UpdateMateriaDTO();

        dto.setMateria("Geografia");
        dto.setId_maestro(111L);

        Materia materia = new Materia();

        materia.setId_materia(432L);
        materia.setMateria("Quimica");
        materia.setId_maestro(111L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(materia));

        service.update(432L, dto);

        assertEquals(dto.getMateria(), materia.getMateria());
        assertEquals(dto.getId_maestro(), materia.getId_maestro());
        verify(repository, times(1)).save(materia);
    }

    @Test
    @DisplayName("El servicio debería llamar un error si la materia no fue encontrada")
    void deleteWithErrorTest() {
        Optional<Materia> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MateriaNotFoundException.class, () -> service.delete(345L));
    }

    @Test
    @DisplayName("El servicio debe borrar un materia por el id en el repositorio")
    void deleteByIdTest() throws MateriaNotFoundException {
        Materia materia = new Materia();

        materia.setId_materia(456L);
        materia.setMateria("Matematicas");
        materia.setId_maestro(221L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(materia));

        service.delete(456L);

        verify(repository, times(1)).deleteById(456L);
    }
}