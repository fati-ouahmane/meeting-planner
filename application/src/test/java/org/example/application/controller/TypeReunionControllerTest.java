package org.example.application.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.domain.DTO.SalleDTO;
import org.example.domain.DTO.TypeReunionDTO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {TypeReunionController.class, TypeReunionServicePort.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@EnableWebMvc
class TypeReunionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeReunionServicePort typeReunionServicePort;


    @Test
    void addTypeReunion_ReturnsTypeReunionDTO() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReunionDTO typeReunionDTO =   TypeReunionDTO.builder()
                .libelle("VC")
                .equipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"))
                .build();


        String   TypeReunionDTOJson = objectMapper.writeValueAsString(typeReunionDTO);


        given(typeReunionServicePort.addTypeReunion(any(TypeReunionDTO.class))).willReturn(typeReunionDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/addTypeReunion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TypeReunionDTOJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.libelle", equalTo("VC")))
                .andExpect(jsonPath("$.equipements", equalTo(Arrays.asList("Ecran", "Webcam", "Pieuvre"))))
                .andReturn();


        TypeReunionDTO returnedTypeReunionDTO = objectMapper.readValue(result.getResponse().getContentAsString(),TypeReunionDTO.class);


        assertEquals(typeReunionDTO.getLibelle(), returnedTypeReunionDTO.getLibelle());
        assertEquals(typeReunionDTO.getEquipements(),returnedTypeReunionDTO.getEquipements());



    }

    @Test
    void getTypesReunion() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();


        List<TypeReunionDTO> typeReunionDTOS = new ArrayList<>();
        TypeReunionDTO typeReunion1 =TypeReunionDTO.builder()
                .libelle("VC")
                .equipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"))
                .build();

        typeReunionDTOS.add(typeReunion1);
        TypeReunionDTO typeReunion2 =TypeReunionDTO.builder()
                .libelle("SPEC")
                .equipements(Arrays.asList(""))
                .build();

        typeReunionDTOS.add(typeReunion2);


        String typeReunionDTOJson = objectMapper.writeValueAsString(typeReunionDTOS);

        when(typeReunionServicePort.getAllTypesReunion()).thenReturn(typeReunionDTOS);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/typereunion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(typeReunionDTOJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(typeReunionDTOS.size())))
                .andExpect(jsonPath("$[0].libelle", equalTo(typeReunion1.getLibelle())))
                .andExpect(jsonPath("$[0].equipements", hasSize(typeReunion1.getEquipements().size())))
                .andExpect(jsonPath("$[0].equipements", containsInAnyOrder(typeReunion1.getEquipements().toArray())))

                .andExpect(jsonPath("$[1].libelle", equalTo(typeReunion2.getLibelle())))
                .andExpect(jsonPath("$[1].equipements", hasSize(typeReunion2.getEquipements().size())))
                .andExpect(jsonPath("$[1].equipements", containsInAnyOrder(typeReunion2.getEquipements().toArray())))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        List<TypeReunionDTO> responseList = objectMapper.readValue(responseJson, new TypeReference<List<TypeReunionDTO>>() {});



        assertEquals(typeReunionDTOS.size(), responseList.size());
        for (int i = 0; i < typeReunionDTOS.size(); i++) {
            TypeReunionDTO expected = typeReunionDTOS.get(i);
            TypeReunionDTO actual = responseList.get(i);
            assertEquals(expected, actual);
        }
    }
}