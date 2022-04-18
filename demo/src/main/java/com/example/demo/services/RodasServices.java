package com.example.demo.services;


import com.example.demo.model.Rodas;
import com.example.demo.repository.RodasRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RodasServices  {

    /*@Autowired
    RodasRepository rodasRepository;*/

    final RodasRepository rodasRepository;

    //--------------------------------------------------------------------------------------------------
    public RodasServices(RodasRepository rodasRepository) {
        this.rodasRepository = rodasRepository;
    }
    //-------------------------------------------CRUD---------------------------------------------------
    @Transactional
    public Rodas save(Rodas rodas) {
        return rodasRepository.save(rodas);
    }

    public List<Rodas> findAll() {
        return rodasRepository.findAll();
    }

    @Transactional
    public Optional<Rodas> findById(Long id){
        return rodasRepository.findById(id);
    }

    @Transactional
    public void removerRoda(Rodas rodas){

        rodasRepository.delete(rodas);

    }

    /*public void updateMarca(Rodas rodas){
        rodasRepository.update(rodas);
    }*/


}
