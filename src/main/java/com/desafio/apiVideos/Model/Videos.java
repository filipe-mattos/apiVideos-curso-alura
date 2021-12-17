package com.desafio.apiVideos.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //Indicando que essa classe sera uma tabela no banco de dados
public class Videos implements Serializable {
    @Id//indicando que essa variavel sera um id e chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)//indicando que ela sera de auto incremento
    private Long idVideos;

    @JoinColumn()//Anotação que indica que a chave estrangeira n pode ser nula
    @ManyToOne//Defino que essa chave estrangeira sera de muitos para um ou seja muitos videos pertencem a uma categoria
    private Categorias idCategoria = new Categorias(1L,"Livre","#ffffff");

    @Column(name = "tituloVideo", nullable = false)//indicando que essa variavel tera o nome passado em name como coluna no bd
    @NotEmpty(message = "O campo de titulo do video não pode ser vazio")
    private String tituloVideo;

    @Column(name = "descricao")//indicando que essa variavel tera o nome passado em name como coluna no bd
    @NotEmpty(message = "O campo de Descricao do video não pode ser vazio")
    private String descricao;

    @Column(name = "url", nullable = false)//indicando que essa variavel tera o nome passado em name como coluna no bd
    @NotEmpty(message = "O campo de url do video não pode ser vazio")
    @URL(message = "Insira uma url valida")
    private String url;

}
