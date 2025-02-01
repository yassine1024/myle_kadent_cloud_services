package com.healthcaredental.reception.rendezvous;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.medecin.Medecin;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(properties = "spring.config.name=application-test")
@AutoConfigureMockMvc
class RendezvousControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RendezvousService rendezvousService;

    @SneakyThrows
    @Test
    void addRendezvous() {

        String patientId= "-1219625691";

        Rendezvous rendezvous = new Rendezvous();
        //rendezvous.setPatient(new Patient("-1219625691"));
        rendezvous.setMedecin(new Medecin("524-44-5151"));
        rendezvous.setDate("2023-04-20");
        rendezvous.setTime("19:55");

        String rendezvousJS= objectMapper.writeValueAsString(rendezvous);

        MvcResult result= mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/patients/{patientId}/rendezvous",patientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rendezvousJS)
                )
                        .andReturn();

        int statusCode = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();

        System.out.println("status: "+statusCode+" && content: "+content);

    }
}