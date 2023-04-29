package com.healthcaredental.reception.cabinet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(properties = "spring.config.name=application-test")
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(CabinetController.class)
@AutoConfigureMockMvc
public class CabinetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CabinetService cabinetService;

    @Test
    public void testGetAllCabinet() throws Exception {
        List<Cabinet> cabinets = new ArrayList<>();
        Cabinet cabinet = new Cabinet();
        cabinet.setId("576-40-1283");
        cabinet.setName("Marty Reilly");
        cabinets.add(cabinet);
        Cabinet cabinet2 = new Cabinet();
        cabinet2.setId("740-04-7106");
        cabinet2.setName("farouk");
        cabinets.add(cabinet2);

        when(cabinetService.getAllCabinet()).thenReturn(cabinets);

        mockMvc.perform(get("/api/cabinet"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("576-40-1283"))
                .andExpect(jsonPath("$[0].name").value("Marty Reilly"))
                .andExpect(jsonPath("$[1].id").value("740-04-7106"))
                .andExpect(jsonPath("$[1].name").value("Cabinet B"));

        verify(cabinetService, times(1)).getAllCabinet();
    }

    @Test
    public void testGetCabinet() throws Exception {
        String cabinetId = "576-40-1283";
        Cabinet cabinet = new Cabinet();
        cabinet.setId(cabinetId);
        cabinet.setName("Marty Reilly");
        when(cabinetService.getCabinet(cabinetId)).thenReturn(cabinet);

        mockMvc.perform(get("/api/cabinet/{id}", cabinetId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(cabinetId))
                .andExpect(jsonPath("$.name").value("Marty Reilly"));

        verify(cabinetService, times(1)).getCabinet(cabinetId);
    }

    @Test
    public void testAddCabinet() throws Exception {
        Cabinet cabinet = new Cabinet();

        mockMvc.perform(post("/api/cabinet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cabinet)))
                .andExpect(status().isOk());

        verify(cabinetService, times(1)).addCabinet(cabinet);
    }
/*
    @Test
    public void testUpdateCabinet() throws Exception {
        long cabinetId = 1L;
        Cabinet cabinet = new Cabinet(cabinetId, "Cabinet A");

        mockMvc.perform(put("/api/cabinet/{id}", cabinetId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cabinet)))
                .andExpect(status().isOk());

        verify(cabinetService, times(1)).updateCabinet(cabinet);
    }

    @Test
    public void testDeleteCabinet() throws Exception {
        long cabinetId = 1L;

        mockMvc.perform(delete("/api/cabinet/{id}", cabinetId))
                .andExpect(status().isOk());

        verify(cabinetService, times(1)).deleteCabinet(cabinetId);
    }

    private static String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }*/
}
