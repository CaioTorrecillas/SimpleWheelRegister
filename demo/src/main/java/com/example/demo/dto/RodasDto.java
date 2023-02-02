package com.example.demo.dto;

import com.example.demo.model.Marca;
import com.example.demo.model.Rodas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RodasDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    @NotBlank(message = "Campo MATERIAL não pode ser nulo.")
    @Length(min = 3, max = 40, message = "O campo MATERIAL deve ter no minimo 3 e no maximo 40 caracteres")
    private String material;

    @NotNull(message="O Raio da sua roda é necessario!")
    private Integer raio;
    private Marca marca;
    private double area;

    public RodasDto(Rodas rodas) {
        this.id = rodas.getId();
        this.marca = rodas.getMarca();
        this.material = rodas.getMaterial();
        this.raio = rodas.getRaio();
        this.area = getArea();

    }


    public double getArea() {
        if(raio != null){
            area = 3.14 * raio * raio;
        }
        return area;
    }


}
