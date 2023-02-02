package com.example.demo.controller;


import com.example.demo.dto.MarcaDto;
import com.example.demo.exceptions.MarcasNotFoundException;
import com.example.demo.model.Marca;
import com.example.demo.services.MarcaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaServices marcaServices;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){

        //return ResponseEntity.status(HttpStatus.OK).body(marcaServices.findById(id));
        try{
            MarcaDto obj = marcaServices.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    @GetMapping
    public ResponseEntity findAll(){
        //Outra maneira de converter uma lista normal para uma lista de dto
        try{
            List<MarcaDto> marcasDto = marcaServices.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(marcasDto);
        }catch (MarcasNotFoundException e){
           String errorMessage =  e.marcaErrorWarningMessage();
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

        }

    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Marca marca){
        try{
            marca = marcaServices.create(marca);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marca.getId()).toUri();
            return ResponseEntity.status(HttpStatus.OK).body(new MarcaDto(marca));
        }catch (MarcasNotFoundException e){
            String errorMessage = e.marcaErrorWarningMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @PathVariable (value = "id") Long id,
                                           @RequestBody MarcaDto marcaDto){
        try{
            marcaServices.update(id, marcaDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(MarcasNotFoundException e){
            String errorMessage = e.marcaErrorWarningMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable (value = "id") Long id){

        try{
            marcaServices.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


}
