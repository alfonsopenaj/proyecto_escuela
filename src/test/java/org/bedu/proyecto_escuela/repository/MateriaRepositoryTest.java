package org.bedu.proyecto_escuela.repository;

import org.bedu.proyecto_escuela.model.Materia;
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
class MateriaRepositoryTest {
    @Autowired
    private MateriaRepository repository;

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
        Iterable materias = repository.findAll();

        assertThat(materias).isEmpty();
    }

    @Test
    @DisplayName("El repositorio deberia devolver todos las materias")
    void findAllTest() {
        Materia materia1 = new Materia();
        Materia materia2 = new Materia();
        Materia materia3 = new Materia();

        materia1.setMateria("Quimica");
        materia1.setId_maestro(213L);

        materia2.setMateria("Biologia");
        materia2.setId_maestro(245L);

        materia3.setMateria("Fisica");
        materia3.setId_maestro(864L);

        manager.persist(materia1);
        manager.persist(materia2);
        manager.persist(materia3);

        List<Materia> result = repository.findAll();

        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("El repositorio deberia devolver una materia por el ID")
    public void findById() {
        Materia materia1 = new Materia();
        Materia materia2 = new Materia();

        materia1.setMateria("Quimica");
        materia1.setId_maestro(213L);

        materia2.setMateria("Biologia");
        materia2.setId_maestro(245L);

        manager.persist(materia1);
        manager.persist(materia2);

        Materia result = repository.findById(materia2.getId_materia()).get();

        assertThat(result).isEqualTo(materia2);
    }

    @Test
    @DisplayName("El repositorio deberia borrar una materia por el ID")
    public void deleteById() {
        Materia materia1 = new Materia();
        Materia materia2 = new Materia();
        Materia materia3 = new Materia();

        materia1.setMateria("Quimica");
        materia1.setId_maestro(213L);

        materia2.setMateria("Biologia");
        materia2.setId_maestro(245L);

        materia3.setMateria("Fisica");
        materia3.setId_maestro(864L);

        manager.persist(materia1);
        manager.persist(materia2);
        manager.persist(materia3);

        repository.deleteById(materia2.getId_materia());

        Iterable result = repository.findAll();

        assertThat(result).hasSize(2).contains(materia1, materia3);
    }

    @Test
    @DisplayName("El repositorio deberia estar vacio")
    public void deleteAllTest() {
        Materia materia1 = new Materia();
        Materia materia2 = new Materia();

        materia1.setMateria("Quimica");
        materia1.setId_maestro(213L);

        materia2.setMateria("Biologia");
        materia2.setId_maestro(245L);

        manager.persist(materia1);
        manager.persist(materia2);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }
}