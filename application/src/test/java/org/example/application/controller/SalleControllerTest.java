package org.example.application.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.domain.DTO.ReservationDTO;
import org.example.domain.DTO.SalleDTO;
import org.example.domain.ports.api.ReservationServicePort;
import org.example.domain.ports.api.SalleServicePort;
import org.example.domain.ports.api.TypeReunionServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {SalleController.class, SalleServicePort.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@EnableWebMvc

class SalleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalleServicePort salleServicePort;


    @Test
    void addSalle_ReturnsSalleDTO() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(module);

        SalleDTO salleDTO = SalleDTO.builder()
                .nom("E3001")
                .capaciteMax(13)
                .equipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"))
                .build();

        String salleDTOJson = objectMapper.writeValueAsString(salleDTO);


        given(salleServicePort.addSalle(any(SalleDTO.class))).willReturn(salleDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/addSalle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salleDTOJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom", equalTo("E3001")))
                .andExpect(jsonPath("$.capaciteMax", equalTo(13)))
                .andExpect(jsonPath("$.equipements", equalTo(Arrays.asList("Ecran", "Webcam", "Pieuvre"))))
                .andReturn();


        SalleDTO returnedSalleDTO = objectMapper.readValue(result.getResponse().getContentAsString(),SalleDTO.class);


        assertEquals(salleDTO.getNom(), returnedSalleDTO.getNom());
        assertEquals(salleDTO.getCapaciteMax(), returnedSalleDTO.getCapaciteMax());
        assertEquals(salleDTO.getEquipements(), returnedSalleDTO.getEquipements());
        assertEquals(salleDTO.getFinOccupation(), returnedSalleDTO.getFinOccupation());


    }

    @Test
    void testgetSalles() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(module);

        List<SalleDTO> salleDTOS = new ArrayList<>();
        SalleDTO salle1=SalleDTO.builder()
                .nom("E3001")
                .capaciteMax(13)
                .equipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"))
                .build();
        salleDTOS.add(salle1);
        SalleDTO salle2 =SalleDTO.builder()
                .nom("E1001")
                .capaciteMax(8)
                .equipements(Arrays.asList("Ecran", "Webcam"))
                .build();
        salleDTOS.add(salle2);


        String salleDTOJson = objectMapper.writeValueAsString(salleDTOS);

        when(salleServicePort.getAllSalles()).thenReturn(salleDTOS);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/salles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salleDTOJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(salleDTOS.size())))
                .andExpect(jsonPath("$[0].nom", equalTo(salle1.getNom())))
                .andExpect(jsonPath("$[0].capaciteMax", equalTo(salle1.getCapaciteMax())))
                .andExpect(jsonPath("$[0].equipements", hasSize(salle1.getEquipements().size())))
                .andExpect(jsonPath("$[0].equipements", containsInAnyOrder(salle1.getEquipements().toArray())))

                .andExpect(jsonPath("$[1].nom", equalTo(salle2.getNom())))
                .andExpect(jsonPath("$[1].capaciteMax", equalTo(salle2.getCapaciteMax())))
                .andExpect(jsonPath("$[1].equipements", hasSize(salle2.getEquipements().size())))
                .andExpect(jsonPath("$[1].equipements", containsInAnyOrder(salle2.getEquipements().toArray())))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        List<SalleDTO> responseList = objectMapper.readValue(responseJson, new TypeReference<List<SalleDTO>>() {});



        assertEquals(salleDTOS.size(), responseList.size());
        for (int i = 0; i < salleDTOS.size(); i++) {
            SalleDTO expected = salleDTOS.get(i);
            SalleDTO actual = responseList.get(i);
            assertEquals(expected, actual);
        }


    }

}