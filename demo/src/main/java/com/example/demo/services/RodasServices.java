package com.example.demo.services;


import com.example.demo.dto.MarcaDto;
import com.example.demo.dto.RodasDto;
import com.example.demo.exceptions.RodasNotFoundException;
import com.example.demo.model.Marca;
import com.example.demo.model.Rodas;
import com.example.demo.repository.RodasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RodasServices  {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RodasRepository rodasRepository;
    @Autowired
    private MarcaServices marcaServices;


    public List<Rodas> findAllByMarca(Long id_marca){
        marcaServices.findById(id_marca);
        return rodasRepository.findAllByMarca(id_marca);

    }


    public List<RodasDto> findAll() throws RodasNotFoundException{
        return rodasRepository
                .findAll()
                .stream()
                .map(rodas -> modelMapper.map(rodas, RodasDto.class))
                .collect(Collectors.toList());

    }

    public Rodas save(Long id_marca, RodasDto rodasDto){

            MarcaDto marcaDto = marcaServices.findById(id_marca);

            Marca marca = new Marca();
            if(!marcaDto.equals(null)){
                marca.setNome(marcaDto.getNome());
                marca.setId(id_marca);
                rodasDto.setMarca(marca);
                Rodas rodasRequest = modelMapper.map(rodasDto, Rodas.class);
                return rodasRepository.save(rodasRequest);
            }else{
                System.out.println("Marca necessary. Returning Error");
                throw new RodasNotFoundException();
            }


    }


    public RodasDto findById(Long id){
        Optional<Rodas> rodas = rodasRepository.findById(id);
            if(!rodas.isEmpty()){
                Optional<RodasDto> rodasDto = Optional.of(new RodasDto(rodas.get()));
                return rodasDto.get();
            }else {
                System.out.println("Roda nao encontrada, lancando erro");
                throw new RodasNotFoundException();
            }

    }

    public void delete(Long id){
        try{
            RodasDto rodasDto = findById(id);
            if(!rodasDto.equals(null)){
                this.rodasRepository.deleteById(id);
                System.out.println("Deletando Rodas");
            }else{
                System.out.println("Roda nao encontrada, retornando erro");
            }
        }catch(Exception e){
            e.getMessage();

        }



    }

    public void update(Long id, RodasDto rodasDto){
        RodasDto rDto = findById(id);
        Rodas rodas = new Rodas();
        rodas.setId(id);
        try{
            if(rDto != null){

                rodas.setMarca(rodasDto.getMarca());
                rodas.setMaterial(rodasDto.getMaterial());
                rodas.setRaio(rodasDto.getRaio());

                rodasRepository.save(rodas);
                System.out.println("Rodas editadas com sucesso");

            }
        }catch(Exception e){
            System.out.println("Retornando erro");
            e.getMessage();
        }
    }




















}
