package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.RodasDto;
import com.example.demo.model.Rodas;
import com.example.demo.services.RodasServices;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //Anotacoes de cross origin para permitir que seja acessado de qualquer fonte
@RequestMapping("/api/demo")  //Onde vao ficar os end points
                              //No controller ficam os metodos get,delete,update, pot...Mapping.
public class DemoController  {

    /*@AutoWired temos preferencia para outra instancia
    private final RodasRepository rodasRepository;

    public DemoController(RodasRepository rodasRepository) {
        this.rodasRepository = rodasRepository;
    }

    Vamos colcoar um DTO depois!!

    O que acontece? No nosso servilege temos o metodo de chamada do GET

    Podemos usar tambem o @RequestMapping(method = RequestMethod.GET)*/

    final RodasServices rodasServices;
    public DemoController(RodasServices rodasServices){
        this.rodasServices = rodasServices;

    }


    @GetMapping
    public ResponseEntity<List<Rodas>> getAllRodas(){

        return ResponseEntity.status(HttpStatus.OK).body(rodasServices.findAll());
    }


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid RodasDto rodasDto){
        var rodas = new Rodas();
        BeanUtils.copyProperties(rodasDto, rodas);
        return ResponseEntity.status(HttpStatus.CREATED).body(rodasServices.save(rodas));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value ="id") Long id){
        Optional<Rodas> rodasOptional = rodasServices.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(rodasOptional.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoda(@PathVariable(value = "id") Long id){
      Optional<Rodas> rodasOptional = rodasServices.findById(id);


      rodasServices.removerRoda(rodasOptional.get());
      return ResponseEntity.status(HttpStatus.OK).body(rodasOptional.get());


    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMarca(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid RodasDto rodasDto){

        Optional<Rodas> rodasOptional = rodasServices.findById(id);
        var rodas = rodasOptional.get();

        rodas.setMarca(rodasDto.getMarca());
        rodas.setMaterial(rodasDto.getMaterial());
        rodas.setRaio(rodasDto.getRaio());

        return ResponseEntity.status(HttpStatus.OK).body(rodasServices.save(rodas));



    }




}
