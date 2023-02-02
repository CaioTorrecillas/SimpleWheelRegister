package com.example.demo.services;

import com.example.demo.dto.MarcaDto;
import com.example.demo.exceptions.MarcasNotFoundException;
import com.example.demo.model.Marca;
import com.example.demo.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaServices {

    @Autowired
    private MarcaRepository marcaRepository;



    public MarcaDto findById(Long id) {
        Optional<Marca> obj = marcaRepository.findById(id);

        if(!obj.isEmpty()){
            MarcaDto marcaDto = new MarcaDto();
            marcaDto.setNome(obj.get().getNome());

            return marcaDto;
        }else{
            throw new MarcasNotFoundException();
        }

    }
    public List<MarcaDto> findAll(){

            List<Marca> marcas = marcaRepository.findAll();
        if(!marcas.isEmpty()){
            List<MarcaDto> marcasDto = marcas.stream()
                    .map(MarcaDto::new)
                    .collect(Collectors.toList());
            return marcasDto;

        }else{
            throw new MarcasNotFoundException();
        }



    }
    public Marca create(Marca marca){
        Optional<Marca> marcaId = marcaRepository.findById(marca.getId());
        if(marcaId.isEmpty()){
            return marcaRepository.save(marca);
        }else{
            throw new MarcasNotFoundException();
        }

    }

    public void update(Long id, MarcaDto marcaDto){
        marcaDto = findById(id);
        if(marcaDto != null){
            Marca marca = new Marca();
            marca.setId(id);
            marca.setNome(marcaDto.getNome());
        }else{
            throw new MarcasNotFoundException();
        }





    }

    public void delete(Long id){
        findById(id);
        marcaRepository.deleteById(id);
    }
}
