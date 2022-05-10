package com.example.demo.services;


import com.example.demo.dto.RodasDto;
import com.example.demo.exceptions.RodasNotFoundException;
import com.example.demo.model.Marca;
import com.example.demo.model.Rodas;
import com.example.demo.repository.RodasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //--------------------------------------------------------------------------------------------------
    /*public RodasServices(RodasRepository rodasRepository) {
        this.rodasRepository = rodasRepository;
    }
    public RodasServices(MarcaServices marcaServices){
        this.marcaServices = marcaServices;
    }*/
    //-------------------------------------------CRUD---------------------------------------------------



    /*@Transactional
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

    }*/

//ANTIGO
    /*public ResponseEntity<List<RodasDto>> findAll(){
        List<Rodas> modelList = rodasRepository.findAll();
        ArrayList<RodasDto> dtoList = new ArrayList<RodasDto>();
        if(modelList != null){
            RodasDto rodasDto;
            for (Rodas rodasModel : modelList){
                rodasDto = new RodasDto();
                BeanUtils.copyProperties(rodasModel, rodasDto);
                dtoList.add(rodasDto);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }*/


    //find all do marca
    public List<Rodas> findAllByMarca(Long id_marca){
        marcaServices.findById(id_marca);
        return rodasRepository.findAllByMarca(id_marca);

    }


    public List<RodasDto> findAll() throws RodasNotFoundException{
        return rodasRepository.findAll().stream().map(rodas -> modelMapper.map(rodas, RodasDto.class))
                .collect(Collectors.toList());


    }

    public Rodas save(Long id_marca, RodasDto rodasDto){

        rodasDto.setId(null);


        Marca marca = marcaServices.findById(id_marca);
        rodasDto.setMarca(marca);

        Rodas rodasRequest = modelMapper.map(rodasDto, Rodas.class);

        return rodasRepository.save(rodasRequest);




        /* public Rodas update(Long id, RodasDto obj){
        Optional<Rodas> rodasOptional = rodasRepository.findById(id);
        var rodas = new Rodas();
        rodas = modelMapper.map(obj, Rodas.class);
        rodas.setMaterial(rodasOptional.get().getMaterial());
        rodas.setRaio(rodasOptional.get().getRaio());
        return rodasRepository.save(rodas);



    }*/


    }

    //FindById
    public Rodas findById(Long id){
        Optional<Rodas> rodas = rodasRepository.findById(id);
        return rodas.orElseThrow(() -> new RodasNotFoundException());

    }



    //FINDBYID DTO
    /*public RodasDto findById(Long id)  throws RodasNotFoundException {
        Optional<Rodas> rodas = rodasRepository.findById(id);
        RodasDto rodasDto = modelMapper.map(rodas, RodasDto.class);
        return rodasDto;
    }*/


    //Delete
    public void delete(Long id){

        findById(id);
        rodasRepository.deleteById(id);

    }

    //UPDATE
    /*public Rodas update(Long id, RodasDto rodasDto) throws RodasNotFoundException{
        Rodas rodas = findById(id);

        rodas.setMaterial(rodasDto.getMaterial());
        rodas.setRaio(rodasDto.getRaio());
        return rodasRepository.save(rodas);


    }*/

    public Rodas update(Long id, Rodas rodas){
        Rodas rodasNova = findById(id);

        updateData(rodasNova, rodas);
        rodas = modelMapper.map(rodas, Rodas.class);

        return rodasRepository.save(rodasNova);


    }

    private void updateData(Rodas rodasNova, Rodas obj){
        rodasNova.setMaterial(obj.getMaterial());
        rodasNova.setRaio(obj.getRaio());
    }


















}
