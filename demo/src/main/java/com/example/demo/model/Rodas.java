package com.example.demo.model;


import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_RODAS")

//@Table(name = "nomeTabela")
// Podemos espeficiar com o nome da tabela tambem, mas se o nome dessa classe for o mesmo nome nao tem problema
public class Rodas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Sobre o @GenerateValue = vamos usar uma chave de id sequencial 1,2,3..
    @GeneratedValue(strategy = GenerationType.AUTO) //Estrategia pode depender um pouco de cada banco de dados
    private Long id;

    @Column(length = 200, nullable = false) //Especificacao de colunas length tamanho reservado; nulllAble nao pode deixar nulo
    private String Marca;   //Mesmo nome do front?

    @Column(length = 60, nullable = false)
    private String Material;
    @Column(length = 10, precision = 2, nullable = false)
    private Integer Raio;
    @Column(precision=10, scale=2)
    @Formula(value = "3.14 * Raio * Raio")
    private BigDecimal Area;

    //----------------------Metodos Getters/Setters!--------------------------


    public BigDecimal getArea() {
        return Area;
    }

    public void setArea(BigDecimal area) {
        Area = area;
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

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }
}
