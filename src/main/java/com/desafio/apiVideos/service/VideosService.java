package com.desafio.apiVideos.service;

import com.desafio.apiVideos.Model.Videos;
import com.desafio.apiVideos.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//Indicando para o spring que essa classe sera um service
public class VideosService {

    //Injetando a variavel de reository na classe de service
    @Autowired
    private VideosRepository videosRepository;

    //Metodo para adicionar um video
    //Se o video ja existir na base de dados ira atualizalo
    public Videos salvar(Videos video){
        return videosRepository.save(video);
    }


    //Metodo que retorna todos os videos da base de dados
    public List<Videos> listarVideos(){
        return videosRepository.findAll();
    }

    //Metodo que busca um video pelo seu id
    //retornando o video se for encontrado ou vazio se n encontrar
    public Optional<Videos> buscarPorId(Long id){
        return videosRepository.findById(id);
    }

    //Metodo que vai deletar o video da base de dados pelo seu id
    public void removerPorId(Long id){
        videosRepository.deleteById(id);
    }
}
