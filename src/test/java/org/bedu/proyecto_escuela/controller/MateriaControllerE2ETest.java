package org.bedu.proyecto_escuela.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.proyecto_escuela.dto.MateriaDTO;
import org.bedu.proyecto_escuela.model.Maestro;
import org.bedu.proyecto_escuela.model.Materia;
import org.bedu.proyecto_escuela.repository.MaestroRepository;
import org.bedu.proyecto_escuela.repository.MateriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Anotación para configurar la BD para las pruebas
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Anotacion para trabajar con mock en ambiente MVC:
@AutoConfigureMockMvc
//Sirven para configurar Sping para test
@ExtendWith(SpringExtension.class)
@SpringBootTest

class MateriaControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MateriaRepository repository;

    @Autowired
    private MaestroRepository maestroRepository;

    private ObjectMapper mapper = new ObjectMapper();

    //Before Each sirve para establecer parametros antes de ejecutar las pruebas
    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET /materia deberia regresar una lista vacia")
    void emptyListTest() throws Exception {
        //Realizar una petición Get a través de un Mock, esperando 200
        MvcResult result = mockMvc.perform(get("/materia"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);

    }

    @Test
    @DisplayName("GET /materia debería regresar una lista de materias")
    void findAllTest() throws Exception {
        Materia materia1 = new Materia();
        Materia materia2 = new Materia();
        Materia materia3 = new Materia();

        materia1.setMateria("Espanol");
        materia1.setId_maestro(987987);

        materia2.setMateria("Matematicas");
        materia2.setId_maestro(654654);

        materia3.setMateria("Geografia");
        materia3.setId_maestro(321321);

        repository.save(materia1);
        repository.save(materia2);
        repository.save(materia3);

        MvcResult result = mockMvc.perform(get("/materia"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<MateriaDTO>> listTypeReference = new TypeReference<List<MateriaDTO>>() {
        };

        // Convertimos el JSON a un objeto de Java
        List<MateriaDTO> response = mapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        assertTrue(response.size() == 3);
        assertEquals("Espanol", response.get(0).getMateria());
        assertEquals(materia2.getMateria(), response.get(1).getMateria());
        assertEquals(materia3.getMateria(), response.get(2).getMateria());
        assertEquals(materia1.getId_maestro(), response.get(0).getId_maestro());
        assertEquals(materia2.getId_maestro(), response.get(1).getId_maestro());
        assertEquals(materia3.getId_maestro(), response.get(2).getId_maestro());
    }

    @Test
    @DisplayName("POST /materia deberia regresar un error si la materia no se envia")
    void sexoMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/materia").contentType("application/json").content("{\"id_maestro\":\"987987\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must not be blank\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("POST /materia deberia regresar un error si el id de maestro no se envia")
    void telefonoMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/materia").contentType("application/json").content("{\"materia\":\"espaniol\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse2 = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must be greater than or equal to 1\"]}";

        assertEquals(expectedResponse2, content);
    }

    @Test
    @DisplayName("PUT /materia/{id} deberia actualizar la información de una materia")
    void updateTest() throws Exception {
        Maestro maestro = new Maestro();

        maestro.setNombre_maestro("Marcela");

        maestroRepository.save(maestro);

        Materia materia = new Materia();

        materia.setMateria("Quimica");
        materia.setId_maestro(maestro.getId_maestro());

        repository.save(materia);

        MvcResult result = mockMvc.perform(put("/materia/" + materia.getId_materia()).contentType("application/json").content("{\"materia\":\"Bioquimica\",\"id_maestro\":" + maestro.getId_maestro() + "}"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"id_materia\":" + materia.getId_materia() + ",\"materia\":\"Bioquimica\",\"id_maestro\":" + maestro.getId_maestro() + "}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("DELETE /maestro/{id} deberia eliminar un maestro")
    void deleteTest() throws Exception {
        Maestro maestro = new Maestro();

        maestro.setNombre_maestro("Marcela");

        maestroRepository.save(maestro);

        Materia materia = new Materia();

        materia.setMateria("Quimica");
        materia.setId_maestro(maestro.getId_maestro());

        repository.save(materia);

        MvcResult result = mockMvc.perform(delete("/materia/" + materia.getId_materia()).contentType("application/json").content(""))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "";

        assertEquals(expectedResponse, content);
    }
}
