package org.example.application.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ReservationController.class, ReservationServicePort.class, SalleServicePort.class, TypeReunionServicePort.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@EnableWebMvc
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationServicePort reservationServicePort;



    @Test
    public void addReservation_ReturnsReservationDTO() throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(module);

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .debut(LocalTime.of(9, 0))
                .fin(LocalTime.of(10, 0))
                .typeReunion("VC")
                .nbPersonnes(8)
                .build();

        String reservationDTOJson = objectMapper.writeValueAsString(reservationDTO);


        given(reservationServicePort.addReservation(any(ReservationDTO.class))).willReturn(reservationDTO);



        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/addReservation")
                    .contentType(MediaType.APPLICATION_JSON)
                        .content(reservationDTOJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.debut", equalTo("09:00:00")))
               .andExpect(jsonPath("$.fin", equalTo("10:00:00")))
                .andExpect(jsonPath("$.typeReunion", equalTo("VC")))
                .andExpect(jsonPath("$.nbPersonnes", equalTo(8)))
                .andReturn();


       ReservationDTO returnedReservationDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ReservationDTO.class);

        System.out.println(result.getResponse().getContentAsString());
       assertEquals(reservationDTO.getDebut(), returnedReservationDTO.getDebut());
       assertEquals(reservationDTO.getFin(), returnedReservationDTO.getFin());
        assertEquals(reservationDTO.getTypeReunion(), returnedReservationDTO.getTypeReunion());
        assertEquals(reservationDTO.getNbPersonnes(), returnedReservationDTO.getNbPersonnes());


    }
    @Test
    public void testTrouverSallesDisponibles() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(module);

        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        reservationDTOS.add(ReservationDTO.builder()
                .debut(LocalTime.of(9, 0))
                .fin(LocalTime.of(10, 0))
                .typeReunion("VC")
                .nbPersonnes(8)
                .build());

        String reservationDTOSJson = objectMapper.writeValueAsString(reservationDTOS);
        System.out.println(reservationDTOSJson);


        List<List<Object>> resultList = createSampleResultList();
        when(reservationServicePort.trouverSallesDisponibles(anyList())).thenReturn(resultList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sallesdisponibles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reservationDTOSJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(resultList.size())))
                .andExpect(jsonPath("$[0][0]", equalTo(resultList.get(0).get(0))))
               .andExpect(jsonPath("$[0][1]", equalTo(resultList.get(0).get(1))))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        List<List<Object>> responseList = objectMapper.readValue(responseJson, new TypeReference<List<List<Object>>>() {});
        System.out.println(responseList);



        assertEquals(resultList.size(), responseList.size());
        for (int i = 0; i < resultList.size(); i++) {
            List<Object> expected = resultList.get(i);
            List<Object> actual = responseList.get(i);
            assertEquals(expected, actual);
        }


    }

    private List<List<Object>> createSampleResultList() {
        List<List<Object>> resultList = new ArrayList<>();

        ReservationDTO reservation = ReservationDTO.builder()
                .id(null)
                .debut(LocalTime.of(9, 0))
                .fin(LocalTime.of(10, 0))
                .typeReunion("VC")
                .nbPersonnes(8)
                .build();
        Object convertedReservation = convertReservationToMap(reservation);

        SalleDTO salle = SalleDTO.builder()
                .id(null)
                .nom("E3001")
                .capaciteMax(13)
                .equipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"))
                .finOccupation(LocalTime.of(11, 0, 0))
                .build();
        Object convertedSalle = convertSalleToMap(salle);

        List<Object> sallereservation = new ArrayList<>();
        sallereservation.add(convertedReservation );
        sallereservation.add(convertedSalle);

        resultList.add(sallereservation);

        return resultList;
    }

    private Object convertSalleToMap(SalleDTO salle) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", salle.getId());
        map.put("nom", salle.getNom());
        map.put("capaciteMax", salle.getCapaciteMax());
        map.put("equipements", salle.getEquipements());
        map.put("finOccupation", salle.getFinOccupation().toString());
        return map;
    }

    private Object convertReservationToMap(ReservationDTO reservation) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", reservation.getId());
        map.put("debut", reservation.getDebut().toString());
        map.put("fin", reservation.getFin().toString());
        map.put("typeReunion", reservation.getTypeReunion());
        map.put("nbPersonnes", reservation.getNbPersonnes());
        return map;
    }


}