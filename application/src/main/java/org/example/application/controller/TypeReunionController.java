package org.example.application.controller;


import org.example.domain.DTO.TypeReunionDTO;
import org.example.domain.ports.api.TypeReunionServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TypeReunionController {

    @Autowired
    TypeReunionServicePort typeReunionServicePort;


    @PostMapping("/addTypeReunion")
    public TypeReunionDTO addTypeReunion(@RequestBody TypeReunionDTO typeReunionDTO) {

      return  typeReunionServicePort.addTypeReunion(typeReunionDTO); }



    @GetMapping("/typereunion")
    public List<TypeReunionDTO> getTypesReunion() {
        return typeReunionServicePort.getAllTypesReunion();
    }



}

