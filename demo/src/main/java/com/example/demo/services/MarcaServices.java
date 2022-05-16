package com.example.demo.services;

import com.example.demo.dto.MarcaDto;
import com.example.demo.model.Marca;
import com.example.demo.repository.MarcaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServices {

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    final MarcaRepository marcaRepository;
    public MarcaServices(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


    public Marca findById(Long id) {
        Optional<Marca> obj = marcaRepository.findById(id);
        return obj
                .orElseThrow(()-> new ObjectNotFoundException("Marca não encontrada. Id: " + id , "Tipo: " + Marca.class.getName()));

    }
    public List<Marca> findAll(){
        return marcaRepository.findAll();

    }
    public Marca create(Marca marca){
        marca.setId(null);
        return marcaRepository.save(marca);
    }
    public Marca update(Long id, MarcaDto marcaDto){
        Marca marca = findById(id);
        marca.setNome(marcaDto.getNome());
        return marcaRepository.save(marca);

    }

    public void delete(Long id){
        findById(id);
        marcaRepository.deleteById(id);
    }
}
