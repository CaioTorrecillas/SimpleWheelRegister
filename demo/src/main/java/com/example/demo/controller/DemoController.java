package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.RodasDto;
import com.example.demo.exceptions.RodasNotFoundException;
import com.example.demo.model.Rodas;
import com.example.demo.services.RodasServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/rodas")
public class DemoController  {

    // postman format localhost:8080/api/rodas?marca=73

    final RodasServices rodasServices;
    public DemoController(RodasServices rodasServices){
        this.rodasServices = rodasServices;

    }



    @PostMapping
    public ResponseEntity save(@RequestParam(value = "marca", defaultValue = "0") Long id_marca, // é de marca
                                      @Valid @RequestBody RodasDto rodasDto){
        try{
            Rodas newRodas = rodasServices.save(id_marca, rodasDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/rodas/{id}").buildAndExpand(newRodas.getId()).toUri();
            return ResponseEntity.created(uri).body("Roda criada com sucesso");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    @GetMapping("/marca{marca}")
    public ResponseEntity findAllByMarca(@PathVariable(value = "marca") Long id_marca) {
        try{
            List<Rodas> list = rodasServices.findAllByMarca(id_marca);
            List<RodasDto> listDto = list
                    .stream()
                    .map(obj -> new RodasDto(obj))
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(listDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not return Rodas by marca");
        }




    }
    @GetMapping("/")
    public ResponseEntity findAll() {
        //return ResponseEntity.status(HttpStatus.OK).body(rodasServices.findAll());
        try{
            List<RodasDto> rodasDtos = rodasServices.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(rodasDtos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not find list of Rodas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(value = "id") Long id){
       try{
           RodasDto rodasDto  = this.rodasServices.findById(id);
           if(!rodasDto.equals(null)){
               return ResponseEntity.status(HttpStatus.OK).body(rodasDto);
           }else{
               return null;
           }
       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
       }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable(value = "id") Long id) {
        try{
            rodasServices.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }



    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody RodasDto rodasDto){
        try{
            rodasServices.update(id, rodasDto);
            return ResponseEntity.status(HttpStatus.OK).body("Roda editada com sucesso");
        }catch (RodasNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.errorWarningMessage());
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<RodasDto> updatePatch(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody RodasDto rodasDto){
        Rodas novaRoda = rodasServices.save(id, rodasDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RodasDto(novaRoda));

    }




}
