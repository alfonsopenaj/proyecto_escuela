package org.bedu.proyecto_escuela.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.proyecto_escuela.dto.MaestroDTO;
import org.bedu.proyecto_escuela.model.Alumno;
import org.bedu.proyecto_escuela.model.Maestro;
import org.bedu.proyecto_escuela.repository.MaestroRepository;
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

class MaestroControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MaestroRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    //Before Each sirve para establecer parametros antes de ejecutar las pruebas
    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET /maestro deberia regresar una lista vacia")
    void emptyListTest() throws Exception {
        //Realizar una petición Get a través de un Mock, esperando 200
        MvcResult result = mockMvc.perform(get("/maestro"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);

    }

    @Test
    @DisplayName("GET /maestro deberia regresar una lista de maestros")
    void findAllTest() throws Exception {
        Maestro maestro1 = new Maestro();
        Maestro maestro2 = new Maestro();
        Maestro maestro3 = new Maestro();

        maestro1.setNombre_maestro("Homero");

        maestro2.setNombre_maestro("Bart");

        maestro3.setNombre_maestro("Lisa");

        repository.save(maestro1);
        repository.save(maestro2);
        repository.save(maestro3);

        MvcResult result = mockMvc.perform(get("/maestro"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<MaestroDTO>> listTypeReference = new TypeReference<List<MaestroDTO>>() {};

        // Convertimos el JSON a un objeto de Java
        List<MaestroDTO> response = mapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        assertTrue(response.size() == 3);
        assertEquals(maestro1.getNombre_maestro(), response.get(0).getNombre_maestro());
        assertEquals(maestro2.getNombre_maestro(), response.get(1).getNombre_maestro());
        assertEquals(maestro3.getNombre_maestro(), response.get(2).getNombre_maestro());
    }

    @Test
    @DisplayName("POST /maestro deberia regresar un error si el nombre no se envia")
    void nombreMaestroMissingInRequestBodyTest() throws Exception {

        MvcResult result = mockMvc.perform(post("/maestro").contentType("application/json").content("{}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // { "code": "ERR_VALID", "message": "Hubo un error al validar los datos de
        // entrada", "details": ["El titulo es obligatorio"]}

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must not be blank\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("PUT /maestro/{id} deberia actualizar la información de un maestro")
    void updateTest() throws Exception {
        Maestro maestro = new Maestro();

        maestro.setNombre_maestro("Gabriela");

        repository.save(maestro);

        MvcResult result = mockMvc.perform(put("/maestro/" + maestro.getId_maestro()).contentType("application/json").content("{\"nombre_maestro\":\"Fidel\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"id_maestro\":" + maestro.getId_maestro() + ",\"nombre_maestro\":\"Fidel\"}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("DELETE /maestro/{id} deberia eliminar un maestro")
    void deleteTest() throws Exception {
        Maestro maestro = new Maestro();

        maestro.setNombre_maestro("Marcela");

        repository.save(maestro);

        MvcResult result = mockMvc.perform(delete("/maestro/" + maestro.getId_maestro()).contentType("application/json").content(""))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "";

        assertEquals(expectedResponse, content);
    }
}
