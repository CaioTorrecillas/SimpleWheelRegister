package com.example.demo.model;



import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_MARCA")

public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    @NotEmpty
    @Length(min = 3, max = 40, message = "O campo Nome deve ter no minimo 3 e no maximo 40 caracteres")
    private String nome;

    @OneToMany(mappedBy = "marca")
    private List<Rodas> rodas = new ArrayList<>();

    //---------------------------------------------------------------
    public Marca(){
        super();

    }
    public Marca(String nome){
        super();
        this.nome = nome;
    }
    //---------------------------------------------------------------


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

    //---------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marca)) return false;
        Marca marca = (Marca) o;
        return getId().equals(marca.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
