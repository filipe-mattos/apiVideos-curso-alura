package com.desafio.apiVideos.service;

import com.desafio.apiVideos.Model.Videos;
import com.desafio.apiVideos.repository.CategoriasRepository;
import com.desafio.apiVideos.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service//Indicando para o spring que essa classe sera um service
public class VideosService {

    //Injetando a variavel de reository na classe de service
    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    //Metodo para adicionar um video
    //Se o video ja existir na base de dados ira atualizalo
    public Videos salvar(Videos video){
        if (video.getIdCategoria().equals(null)){
            video.getIdCategoria().setIdCategoria(1L);
        }
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

    public List<Videos> buscarPorCategoria(Long idCategoria){
        List<Videos> videos = listarVideos();
        List<Videos> aux = new ArrayList<>();
        for ( int i =0 ; i < videos.size(); i++){
            if (videos.get(i).getIdCategoria().getIdCategoria().equals(categoriasRepository.getById(idCategoria).getIdCategoria())){
                aux.add(videos.get(i));
                System.out.println("Adicionou no aux");
            }
        }
        return aux;
    }

    //Metodo que vai deletar o video da base de dados pelo seu id
    public void removerPorId(Long id){
        videosRepository.deleteById(id);
    }

    public List<Videos> listarVideosPublicos() {
        List<Videos> list = videosRepository.findAll();
        list = buscaVideosLivres(list);
        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    private List<Videos> buscaVideosLivres(List<Videos> list) {
        List<Videos> aux = new ArrayList<>();
        for (Videos video : list){
            if (video.getIdCategoria().getNomeCategoria().equals("Publico")){
                aux.add(video);
            }
        }
        return aux;
    }
}
