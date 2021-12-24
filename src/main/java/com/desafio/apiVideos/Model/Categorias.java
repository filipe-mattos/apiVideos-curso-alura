package com.desafio.apiVideos.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data//Utilizando a anotação do lombok para criar os geters e seters
@AllArgsConstructor//Utilizando a anotação do lombok para criar um construtor com todos os atributos
@NoArgsConstructor//Utilizando a anotação do lombok para criar um construtor vazio
@Entity
public class Categorias {

    @Id//indicando que essa variavel sera um id e chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)//indicando que ela sera de auto incremento
    private Long idCategoria;

    @Column(name = "nomeCategoria", nullable = false)
    @NotNull @NotEmpty
    private String nomeCategoria;

    @Column(name = "corHexadecimal", nullable = false)
    @NotNull @NotEmpty
    private String cor;

    public Categorias(String nomeCategoria, String cor) {
        this.nomeCategoria = nomeCategoria;
        this.cor = cor;
    }
}
