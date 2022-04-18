package com.example.demo.repository;

import com.example.demo.model.Rodas;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RodasRepository extends JpaRepository <Rodas, Long> {
    /*Vamos passar o Model do repository e o tipo de indentificador (nome da tabela e o tipo de indentificador)
    Apos fazer isso é criado uma implementacao (pdemos clicar em JpaRepository)
    dessa interface onde temos varios metodos como findall, deleteall, flush, getOne, etc.
    Podemos usar findByName tambem e muitos outros
    Vamos jogar o repositorio para o controler mas por enquanto!!
    O jpa repository ja possui metodos prontos para fazer a busca no banco de dados. Ou seja ele simplifica o uso desses metodos/scripts
    Essa interface quando extendemos podemos colocar @Repository u dos estereotipos para transacao com base de dados*/





}
