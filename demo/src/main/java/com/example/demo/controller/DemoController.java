package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.RodasDto;
import com.example.demo.model.Rodas;
import com.example.demo.services.RodasServices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //Anotacoes de cross origin para permitir que seja acessado de qualquer fonte
@RequestMapping("/api/rodas")  //Onde vao ficar os end points
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


    /*@GetMapping
    public ResponseEntity<List<Rodas>> getAllRodas(){

        return ResponseEntity.status(HttpStatus.OK).body(rodasServices.findAll());
    }


    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody RodasDto rodasDto){
        var rodas = new Rodas();
        BeanUtils.copyProperties(rodasDto, rodas);
        return ResponseEntity.status(HttpStatus.CREATED).body(rodasServices.save(rodas));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value ="id") Long id) throws ResourcesNotFoundException{


        Optional<Rodas> rodasOptional = rodasServices.findById(id);
        if(!rodasOptional.isPresent()){
            throw new ResourcesNotFoundException("Essa Roda não existe");

        }else{
            RodasDto rodasDto  = new RodasDto();
            BeanUtils.copyProperties( rodasOptional.get(), rodasDto);
            return ResponseEntity.status(HttpStatus.OK).body(rodasDto);
        }



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoda(@PathVariable(value = "id") Long id) throws
            ResourcesNotFoundException,FieldName {
      Optional<Rodas> rodasOptional = Optional.ofNullable(rodasServices.findById(id))
              .orElseThrow(() -> new ResourcesNotFoundException("Essa Roda nao foi encontrada = " + id));

      rodasServices.removerRoda(rodasOptional.get());
      return ResponseEntity.status(HttpStatus.OK).body(rodasOptional.get());


    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMarca(@PathVariable(value = "id") Long id,
                                              @Valid @RequestBody RodasDto rodasDto) throws ResourcesNotFoundException, FieldName {

        Optional<Rodas> rodasOptional = Optional.ofNullable(rodasServices.findById(id))
                .orElseThrow(()-> new ResourcesNotFoundException("Roda não encontrada = " + id));
        var rodas = rodasOptional.get();

        rodas.setMarca(rodasDto.getMarca());
        rodas.setMaterial(rodasDto.getMaterial());
        rodas.setRaio(rodasDto.getRaio());

        return ResponseEntity.status(HttpStatus.OK).body(rodasServices.save(rodas));



    }*/


    @PostMapping
    public ResponseEntity<Rodas> save(@RequestParam(value = "marca", defaultValue = "0") Long id_marca, // é de marca
                                      @Valid @RequestBody RodasDto rodasDto){


        Rodas newRodas = rodasServices.save(id_marca, rodasDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/rodas/{id}").buildAndExpand(newRodas.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/marca{marca}")
    public ResponseEntity<List<RodasDto>> findAllByMarca(@PathVariable(value = "marca") Long id_marca) {
        List<Rodas> list = rodasServices.findAllByMarca(id_marca);
        List<RodasDto> listDto = list.stream().map(obj -> new RodasDto(obj)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(listDto);

        //localhost:8080/api/rodas?marca=73
        //o valor nao pode chegar vazio, por isso o default value

    }
    @GetMapping
    public ResponseEntity<List<RodasDto>> findAll() {
        /*List<Rodas> list = rodasServices.findAll();
        List<RodasDto> listDto = list.stream().map(obj -> new RodasDto(obj)).collect(Collectors.toList());*/
        return ResponseEntity.status(HttpStatus.OK).body(rodasServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rodas> findById(@PathVariable(value = "id") Long id){
        Rodas rodas  = new Rodas();
        BeanUtils.copyProperties(rodasServices.findById(id), rodas);
        return ResponseEntity.status(HttpStatus.OK).body(rodas);



    }

    /*@GetMapping("/{id}")
    public ResponseEntity<RodasDto> findById(@PathVariable (value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(rodasServices.findById(id));
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        rodasServices.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

    @PutMapping("/{id}")
    public ResponseEntity<Rodas> update(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody Rodas rodas){

        Rodas novaRoda = rodasServices.update(id, rodas);

        return ResponseEntity.status(HttpStatus.OK).body(novaRoda);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<RodasDto> updatePatch(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody RodasDto rodasDto){
        Rodas novaRoda = rodasServices.save(id, rodasDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RodasDto(novaRoda));

    }



}
