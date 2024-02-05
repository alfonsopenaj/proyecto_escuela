package org.bedu.proyecto_escuela.repository;

import org.bedu.proyecto_escuela.model.Maestro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MaestroRepositoryTest {
    @Autowired
    private MaestroRepository repository;

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
        Iterable maestros = repository.findAll();

        assertThat(maestros).isEmpty();
    }

    @Test
    @DisplayName("El repositorio deberia devolver todos los maestros")
    void findAllTest() {
        Maestro maestro1 = new Maestro();
        Maestro maestro2 = new Maestro();
        Maestro maestro3 = new Maestro();

        maestro1.setNombre_maestro("Marcela");
        maestro2.setNombre_maestro("Karina");
        maestro3.setNombre_maestro("Fernando");

        manager.persist(maestro1);
        manager.persist(maestro2);
        manager.persist(maestro3);

        List<Maestro> result = repository.findAll();

        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("El repositorio deberia devolver un maestro por el ID")
    public void findById() {
        Maestro maestro1 = new Maestro();
        Maestro maestro2 = new Maestro();

        maestro1.setNombre_maestro("Marcela");
        maestro2.setNombre_maestro("Karina");

        manager.persist(maestro1);
        manager.persist(maestro2);

        Maestro result = repository.findById(maestro2.getId_maestro()).get();

        assertThat(result).isEqualTo(maestro2);
    }

    @Test
    @DisplayName("El repositorio deberia borrar un maestro por el ID")
    public void deleteById() {
        Maestro maestro1 = new Maestro();
        Maestro maestro2 = new Maestro();
        Maestro maestro3 = new Maestro();

        maestro1.setNombre_maestro("Marcela");
        maestro2.setNombre_maestro("Karina");
        maestro3.setNombre_maestro("Fernando");

        manager.persist(maestro1);
        manager.persist(maestro2);
        manager.persist(maestro3);

        repository.deleteById(maestro2.getId_maestro());

        Iterable result = repository.findAll();

        assertThat(result).hasSize(2).contains(maestro1, maestro3);
    }

    @Test
    @DisplayName("El repositorio deberia estar vacio")
    public void deleteAllTest() {
        Maestro maestro1 = new Maestro();
        Maestro maestro2 = new Maestro();

        maestro1.setNombre_maestro("Marcela");
        maestro2.setNombre_maestro("Karina");

        manager.persist(maestro1);
        manager.persist(maestro2);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }
}