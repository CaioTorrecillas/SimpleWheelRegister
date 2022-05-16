package com.example.demo.dto;

import com.example.demo.model.Marca;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class MarcaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty
    @Length(min = 3, max = 40, message = "O campo Nome deve ter no minimo 3 e no maximo 40 caracteres")
    private String nome;

    //------------------------------------------------------

    public MarcaDto(){
        super();
    }
    public MarcaDto(Marca obj){
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
    }


    //------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
