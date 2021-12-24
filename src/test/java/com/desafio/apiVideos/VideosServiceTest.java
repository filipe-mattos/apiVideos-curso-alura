package com.desafio.apiVideos;

import com.desafio.apiVideos.Model.Categorias;
import com.desafio.apiVideos.Model.Videos;
import com.desafio.apiVideos.service.CategoriasService;
import com.desafio.apiVideos.service.VideosService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideosServiceTest {

    @Autowired
    VideosService videosService;

    @Autowired
    CategoriasService categoriasService;

    @Test
    @Rollback
    public void insertVideo(){
        Videos video = new Videos(1L,
                categoriasService.salvarCategoria(
                        new Categorias(1L,
                                "Livre",
                                "teste")),
                "tituloVideo",
                "descricaoVideo",
                "https://www.google.com.br");

        Assert.assertNotEquals(new Videos(), videosService.salvar(video));
    }

    @Test
    @Rollback
    public void buscarPorId(){
        Videos video = new Videos(2L,
                categoriasService.salvarCategoria(
                        new Categorias(1L,
                                "Livre",
                                "teste")),
                "tituloVideo",
                "descricaoVideo",
                "https://www.google.com.br");

        Assert.assertNotEquals(videosService.buscarPorId(videosService.salvar(video).getIdVideos())
                                ,Optional.empty());
    }

//    @Test
//    @Rollback
//    public void buscarPorNome(){
//        Videos video = new Videos(2L,
//                categoriasService.salvarCategoria(
//                        new Categorias(1L,
//                                "Livre",
//                                "teste")),
//                "tituloVideo",
//                "descricaoVideo",
//                "https://www.google.com.br");
//        videosService.salvar(video);
//        Assert.assertEquals(videosService.buscarPorNome(video.getTituloVideo())
//                ,video);
//    }

    @Test
    @Rollback
    public void deleteVideo(){
        Videos video = new Videos(1L,
                categoriasService.salvarCategoria(
                        new Categorias(1L,
                                "Livre",
                                "teste")),
                "tituloVideo",
                "descricaoVideo",
                "https://www.google.com.br");
        videosService.removerPorId(videosService.salvar(video).getIdVideos());
        Assert.assertEquals(videosService.buscarPorId(video.getIdVideos())
                ,Optional.empty());
    }

    @Test
    @Rollback
    public void buscarVideoPorCategoria(){
        Videos video = new Videos(1L,
                categoriasService.salvarCategoria(
                        new Categorias(1L,
                                "Livre",
                                "teste")),
                "tituloVideo",
                "descricaoVideo",
                "https://www.google.com.br");
        List<Videos> listaVideos = new ArrayList();
        listaVideos.add(videosService.salvar(video));

        Assert.assertEquals(listaVideos,
                            videosService.buscarPorCategoria(listaVideos.get(0).getIdCategoria().getIdCategoria()));
    }

    @Test
    @Rollback
    public void listarVideos(){
        Videos video = new Videos(1L,
                categoriasService.salvarCategoria(
                        new Categorias(1L,
                                "Livre",
                                "teste")),
                "tituloVideo",
                "descricaoVideo",
                "https://www.google.com.br");

        List<Videos> listaVideos = new ArrayList();
        listaVideos.add(videosService.salvar(video));
        listaVideos.add(videosService.salvar(video));

        Assert.assertEquals(listaVideos,
                            videosService.listarVideos());
    }


}
