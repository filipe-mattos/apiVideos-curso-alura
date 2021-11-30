package com.desafio.apiVideos.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //Indicando que essa classe sera uma tabela no banco de dados
public class Videos {
    @Id//indicando que essa variavel sera um id e chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)//indicando que ela sera de auto incremento
    private Long id;

    @Column(name = "tituloVideo", nullable = false)//indicando que essa variavel tera o nome passado em name como coluna no bd
    private String tituloVideo;

    @Column(name = "descricao")//indicando que essa variavel tera o nome passado em name como coluna no bd
    private String descricao;

    @Column(name = "url", nullable = false)//indicando que essa variavel tera o nome passado em name como coluna no bd
    private String url;

    public Videos(){

    }

    public Videos(Long id, String tituloVideo, String descricao, String url) {
        this.id = id;
        this.tituloVideo = tituloVideo;
        this.descricao = descricao;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloVideo() {
        return tituloVideo;
    }

    public void setTituloVideo(String tituloVideo) {
        this.tituloVideo = tituloVideo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
