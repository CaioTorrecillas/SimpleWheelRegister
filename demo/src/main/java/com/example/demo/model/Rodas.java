package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_RODAS")

//@Table(name = "nomeTabela")
// Podemos espeficiar com o nome da tabela tambem, mas se o nome dessa classe for o mesmo nome nao tem problema
public class Rodas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Sobre o @GenerateValue = vamos usar uma chave de id sequencial 1,2,3..
    @GeneratedValue(strategy = GenerationType.AUTO) //Estrategia pode depender um pouco de cada banco de dados
    private Long id;
    //@Column(length = 200, nullable = false) //Especificacao de colunas length tamanho reservado; nulllAble nao pode deixar nulo
    //@Length(min = 3, max = 40, message = "O campo MARCA deve ter no minimo 3 e no maximo 40 caracteres")
    //private String Marca;   //Mesmo nome do front?

    @NotEmpty(message="O Material da sua roda é necessario!")
    @Length(min = 5, max = 30, message="O campo Material precisa ter no minimo 5 caracteres e no maximo 30!")
    private String Material;

    @NotNull(message="O Raio da sua roda é necessario!")
    private Integer Raio;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;


    //Construtor----------------------

    public Rodas(){
        super();
    }

    public Rodas(Long id, String material, Integer raio, Marca marca) {
        super();
        this.id = id;
        Material = material;
        Raio = raio;
        this.marca = marca;
    }


    //@Formula(value = "3.14 * Raio * Raio")
    //private BigDecimal Area;





    //----------------------Metodos Getters/Setters!--------------------------


//    public BigDecimal getArea() {
//        return Area;
//    }
//
//    public void setArea(BigDecimal area) {
//        Area = area;
//    }


    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Integer getRaio() {
        return Raio;
    }

    public void setRaio(Integer raio) {
        Raio = raio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }*/



    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }
//-----------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rodas)) return false;
        Rodas rodas = (Rodas) o;
        return getId().equals(rodas.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
