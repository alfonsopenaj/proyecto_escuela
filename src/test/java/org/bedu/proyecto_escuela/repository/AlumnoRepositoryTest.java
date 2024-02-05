package org.bedu.proyecto_escuela.repository;

import org.bedu.proyecto_escuela.model.Alumno;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AlumnoRepositoryTest {
    @Autowired
    private AlumnoRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    @DisplayName("El repositorio deberia ser inyectado")
    void smokeTest() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("El repositorio deberia devolver que está vacio")
    public void isEmptyTest() {
        Iterable alumnos = repository.findAll();

        assertThat(alumnos).isEmpty();
    }

    @Test
    @DisplayName("El repositorio deberia devolver todos los alumnos")
    void findAllTest() {
        Alumno alumno1 = new Alumno();
        Alumno alumno2 = new Alumno();
        Alumno alumno3 = new Alumno();

        alumno1.setMatricula(111);
        alumno1.setNombre_alumno("Roberto");
        alumno1.setSexo("M");
        alumno1.setTelefono("9616179700");
        alumno1.setDireccion("Fidel Velazquez");
        alumno1.setEmail("roberto@gmail.com");

        alumno2.setMatricula(222);
        alumno2.setNombre_alumno("Carlos");
        alumno2.setSexo("M");
        alumno2.setTelefono("9616179722");
        alumno2.setDireccion("Las Rosas");
        alumno2.setEmail("carlos@gmail.com");

        alumno3.setMatricula(333);
        alumno3.setNombre_alumno("Patricia");
        alumno3.setSexo("F");
        alumno3.setTelefono("9616179334");
        alumno3.setDireccion("Bugambilias");
        alumno3.setEmail("patricia@gmail.com");

        manager.persist(alumno1);
        manager.persist(alumno2);
        manager.persist(alumno3);

        List<Alumno> result = repository.findAll();

        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("El repositorio deberia devolver un alumno por el ID")
    public void findById() {
        Alumno alumno1 = new Alumno();
        Alumno alumno2 = new Alumno();

        alumno1.setMatricula(111);
        alumno1.setNombre_alumno("Roberto");
        alumno1.setSexo("M");
        alumno1.setTelefono("9616179700");
        alumno1.setDireccion("Fidel Velazquez");
        alumno1.setEmail("roberto@gmail.com");

        alumno2.setMatricula(222);
        alumno2.setNombre_alumno("Carlos");
        alumno2.setSexo("M");
        alumno2.setTelefono("9616179722");
        alumno2.setDireccion("Las Rosas");
        alumno2.setEmail("carlos@gmail.com");

        manager.persist(alumno1);
        manager.persist(alumno2);

        Alumno result = repository.findById(alumno2.getId_alumno()).get();

        assertThat(result).isEqualTo(alumno2);
    }

    @Test
    @DisplayName("El repositorio deberia borrar un alumno por el ID")
    public void deleteById() {
        Alumno alumno1 = new Alumno();
        Alumno alumno2 = new Alumno();
        Alumno alumno3 = new Alumno();

        alumno1.setMatricula(111);
        alumno1.setNombre_alumno("Roberto");
        alumno1.setSexo("M");
        alumno1.setTelefono("9616179700");
        alumno1.setDireccion("Fidel Velazquez");
        alumno1.setEmail("roberto@gmail.com");

        alumno2.setMatricula(222);
        alumno2.setNombre_alumno("Carlos");
        alumno2.setSexo("M");
        alumno2.setTelefono("9616179722");
        alumno2.setDireccion("Las Rosas");
        alumno2.setEmail("carlos@gmail.com");

        alumno3.setMatricula(333);
        alumno3.setNombre_alumno("Patricia");
        alumno3.setSexo("F");
        alumno3.setTelefono("9616179334");
        alumno3.setDireccion("Bugambilias");
        alumno3.setEmail("patricia@gmail.com");

        manager.persist(alumno1);
        manager.persist(alumno2);
        manager.persist(alumno3);

        repository.deleteById(alumno2.getId_alumno());

        Iterable result = repository.findAll();

        assertThat(result).hasSize(2).contains(alumno1, alumno3);
    }

    @Test
    @DisplayName("El repositorio deberia estar vacio")
    public void deleteAllTest() {
        Alumno alumno1 = new Alumno();
        Alumno alumno2 = new Alumno();

        alumno1.setMatricula(111);
        alumno1.setNombre_alumno("Roberto");
        alumno1.setSexo("M");
        alumno1.setTelefono("9616179700");
        alumno1.setDireccion("Fidel Velazquez");
        alumno1.setEmail("roberto@gmail.com");

        alumno2.setMatricula(222);
        alumno2.setNombre_alumno("Carlos");
        alumno2.setSexo("M");
        alumno2.setTelefono("9616179722");
        alumno2.setDireccion("Las Rosas");
        alumno2.setEmail("carlos@gmail.com");

        manager.persist(alumno1);
        manager.persist(alumno2);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }
}