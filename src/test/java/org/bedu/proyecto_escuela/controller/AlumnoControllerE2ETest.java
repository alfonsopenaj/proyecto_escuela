package org.bedu.proyecto_escuela.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.proyecto_escuela.dto.AlumnoDTO;
import org.bedu.proyecto_escuela.dto.MateriaDTO;
import org.bedu.proyecto_escuela.model.Alumno;
import org.bedu.proyecto_escuela.model.AlumnoMateria;
import org.bedu.proyecto_escuela.model.Maestro;
import org.bedu.proyecto_escuela.model.Materia;
import org.bedu.proyecto_escuela.repository.AlumnoMateriaRepository;
import org.bedu.proyecto_escuela.repository.AlumnoRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    @DisplayName("GET /alumno deberia regresar una lista vacia")
    void emptyListTest() throws Exception {
        //Realizar una petición Get a través de un Mock, esperando 200
        MvcResult result = mockMvc.perform(get("/alumno"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);

    }

    @Test
    @DisplayName("GET /alumno deberia regresar una lista de alumnos")
    void findAllTest() throws Exception {
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

        repository.save(alumno1);
        repository.save(alumno2);
        repository.save(alumno3);

        MvcResult result = mockMvc.perform(get("/alumno"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<AlumnoDTO>> listTypeReference = new TypeReference<List<AlumnoDTO>>() {};

        // Convertimos el JSON a un objeto de Java
        List<AlumnoDTO> response = mapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        assertTrue(response.size() == 3);
        assertEquals(alumno1.getNombre_alumno(), response.get(0).getNombre_alumno());
        assertEquals(alumno2.getNombre_alumno(), response.get(1).getNombre_alumno());
        assertEquals(alumno3.getNombre_alumno(), response.get(2).getNombre_alumno());
    }

    @Test
    @DisplayName("POST /alumno deberia regresar un error si el nombre no se envia")
    void nombreAlumnoMissingInRequestBodyTest() throws Exception {

        MvcResult result = mockMvc.perform(post("/alumno").contentType("application/json").content("{\"matricula\":\"456\",\"sexo\":\"H\",\"telefono\":\"9612233466\",\"direccion\":\"Domicilio\",\"email\":\"rodrigo@gmail.com\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // { "code": "ERR_VALID", "message": "Hubo un error al validar los datos de
        // entrada", "details": ["El titulo es obligatorio"]}

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must not be blank\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("POST /alumno deberia regresar un error si el sexo no se envia")
    void sexoMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/alumno").contentType("application/json").content("{\"matricula\":\"456\",\"nombre_alumno\":\"Carlos\",\"telefono\":\"9612233466\",\"direccion\":\"Domicilio\",\"email\":\"rodrigo@gmail.com\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must not be blank\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("POST /alumno deberia regresar un error si el telefono no se envia")
    void telefonoMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/alumno").contentType("application/json").content("{\"matricula\":\"456\",\"nombre_alumno\":\"Carlos\",\"sexo\":\"H\",\"direccion\":\"Domicilio\",\"email\":\"rodrigo@gmail.com\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must not be blank\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("POST /alumno deberia regresar un error si la direccion no se envia")
    void direccionMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/alumno").contentType("application/json").content("{\"matricula\":\"456\",\"nombre_alumno\":\"Carlos\",\"sexo\":\"H\",\"telefono\":\"9988776655\",\"email\":\"rodrigo@gmail.com\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must not be blank\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("POST /alumno deberia regresar un error si el email no se envia")
    void emailMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/alumno").contentType("application/json").content("{\"matricula\":\"456\",\"nombre_alumno\":\"Carlos\",\"sexo\":\"H\",\"telefono\":\"9988776655\",\"direccion\":\"Conocido\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must not be blank\"]}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("PUT /alumno/{id} deberia actualizar la información de un alumno")
    void updateTest() throws Exception {
        Alumno alumno = new Alumno();

        alumno.setMatricula(111);
        alumno.setNombre_alumno("Roberto");
        alumno.setSexo("M");
        alumno.setTelefono("9616179700");
        alumno.setDireccion("Fidel Velazquez");
        alumno.setEmail("roberto@gmail.com");

        repository.save(alumno);

        MvcResult result = mockMvc.perform(put("/alumno/" + alumno.getId_alumno()).contentType("application/json").content("{\"matricula\":\"456\",\"nombre_alumno\":\"Carlos\",\"sexo\":\"H\",\"telefono\":\"9988776655\",\"direccion\":\"Conocido\",\"email\":\"carlos@gmail.com\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"id_alumno\":" + alumno.getId_alumno() + ",\"matricula\":456,\"nombre_alumno\":\"Carlos\",\"sexo\":\"H\",\"telefono\":\"9988776655\",\"direccion\":\"Conocido\",\"email\":\"carlos@gmail.com\"}";

        assertEquals(expectedResponse, content);
    }

    @Test
    @DisplayName("DELETE /alumno/{id} deberia eliminar un alumno")
    void deleteTest() throws Exception {
        Alumno alumno = new Alumno();

        alumno.setMatricula(111);
        alumno.setNombre_alumno("Roberto");
        alumno.setSexo("M");
        alumno.setTelefono("9616179700");
        alumno.setDireccion("Fidel Velazquez");
        alumno.setEmail("roberto@gmail.com");

        repository.save(alumno);

        MvcResult result = mockMvc.perform(delete("/alumno/" + alumno.getId_alumno()).contentType("application/json").content(""))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "";

        assertEquals(expectedResponse, content);
    }
}

