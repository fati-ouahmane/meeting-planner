package org.example.application.controller;


import org.example.domain.DTO.SalleDTO;
import org.example.domain.ports.api.SalleServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SalleController {


    @Autowired
    SalleServicePort salleServicePort;

    @PostMapping("/addSalle")
    public SalleDTO addSalle(@RequestBody SalleDTO salleDTO){

      return salleServicePort.addSalle(salleDTO);
      }

    @GetMapping("/salles")
    public List<SalleDTO> getSalles() {

        return salleServicePort.getAllSalles();
    }
}
