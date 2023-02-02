package com.example.demo.dto;

import com.example.demo.model.Marca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    public MarcaDto(Marca marca){
        this.id = marca.getId();
        this.nome = marca.getNome();
    }


}
