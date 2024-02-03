package org.bedu.proyecto_escuela.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.proyecto_escuela.repository.AlumnoRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Anotación para configurar la BD para las pruebas
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Anotacion para trabajar con mock en ambiente MVC:
@AutoConfigureMockMvc
//Sirven para configurar Sping para test
@ExtendWith(SpringExtension.class)
@SpringBootTest

class AlumnoControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlumnoRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    //Before Each sirve para establecer parametros antes de ejecutar las pruebas
    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET /Alumnos deberia regresar una lista vacia")
    void emptyListTest() throws Exception {
        //Realizar una petición Get a través de un Mock, esperando 200
        MvcResult result = mockMvc.perform(get("/alumno"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();


        assertEquals("[]", content);

    }

    //




}

