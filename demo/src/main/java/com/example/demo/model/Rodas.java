package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_RODAS")


public class Rodas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="O Material da sua roda é necessario!")
    @Length(min = 5, max = 30, message="O campo Material precisa ter no minimo 5 caracteres e no maximo 30!")
    private String Material;

    @NotNull(message="O Raio da sua roda é necessario!")
    private Integer Raio;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

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
