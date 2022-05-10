package com.example.demo.dto;

import com.example.demo.model.Marca;
import com.example.demo.model.Rodas;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class RodasDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    //marca
    //@NotBlank(message = "Campo MARCA não pode ser nulo.")
    //@Length(min = 3, max = 40, message = "O campo MARCA deve ter no minimo 3 e no maximo 40 caracteres")
    //private String Marca;

    @NotBlank(message = "Campo MATERIAL não pode ser nulo.")
    @Length(min = 3, max = 40, message = "O campo MATERIAL deve ter no minimo 3 e no maximo 40 caracteres")
    private String Material;

    @NotNull(message="O Raio da sua roda é necessario!")
    private Integer Raio;

    private Marca marca;

    private double area;

    public RodasDto(){
        super();
    }
    public RodasDto(Rodas obj) {
        super();
        this.id = obj.getId();
        this.marca = obj.getMarca();
        this.Material = obj.getMaterial();
        this.Raio = obj.getRaio();

    }


    //@Column(precision=10, scale=2)
    //@Formula(value = "3.14 * Raio * Raio")
    //private BigDecimal Area;

    //Aqui a area nao aparece no postman
    //@Formula(value = "3.14 * Raio * Raio")





//----------------------------------------------



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getArea() {
        if(Raio != null){
            area = 3.14 * Raio * Raio;
        }
        return area;
    }

    public void setArea(double area) {
            area = area;
    }
    //marca


    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public Integer getRaio() {
        return Raio;
    }

    public void setRaio(Integer raio) {

        Raio = raio;
    }
}
