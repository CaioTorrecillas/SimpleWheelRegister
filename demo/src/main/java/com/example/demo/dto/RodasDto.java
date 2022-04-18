package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RodasDto {

    @NotBlank
    private String Marca;

    @NotBlank
    private String Material;

    @NotNull
    private Integer Raio;



//-----------------------------------------------


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

    public Integer getRaio() {
        return Raio;
    }

    public void setRaio(Integer raio) {
        Raio = raio;
    }
}
