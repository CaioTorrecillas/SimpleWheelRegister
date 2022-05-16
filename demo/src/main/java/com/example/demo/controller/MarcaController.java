package com.example.demo.controller;


import com.example.demo.dto.MarcaDto;
import com.example.demo.model.Marca;
import com.example.demo.services.MarcaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/marca")
public class MarcaController {

    final MarcaServices marcaServices;

    public MarcaController(MarcaServices marcaServices){
        this.marcaServices = marcaServices;

    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){

        //return ResponseEntity.status(HttpStatus.OK).body(marcaServices.findById(id));
        Marca obj = marcaServices.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<MarcaDto>> findAll(){
        //Outra maneira de converter uma lista normal para uma lista de dto
        List<Marca> list = marcaServices.findAll();
        List<MarcaDto> listDto = list.stream().map(obj -> new MarcaDto(obj)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }

    @PostMapping
    public ResponseEntity<MarcaDto> create(@Valid @RequestBody Marca marca){

        marca = marcaServices.create(marca);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marca.getId()).toUri();
        return ResponseEntity.status(HttpStatus.OK).body(new MarcaDto(marca));
    }

    @PutMapping("/{id}")
        public ResponseEntity<MarcaDto> update(@Valid @PathVariable (value = "id") Long id, @RequestBody MarcaDto marcaDto){
        Marca marca = marcaServices.update(id, marcaDto);
        return ResponseEntity.status(HttpStatus.OK).body(new MarcaDto(marca));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable (value = "id") Long id){

        marcaServices.delete(id);
        return ResponseEntity.noContent().build();
    }


}
