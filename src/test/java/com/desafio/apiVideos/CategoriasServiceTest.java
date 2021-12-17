package com.desafio.apiVideos;

import com.desafio.apiVideos.Model.Categorias;
import com.desafio.apiVideos.service.CategoriasService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriasServiceTest {

    @Autowired
    private CategoriasService categoriasService;

    @Test
    public void insertNovaCategoria(){
        Categorias categoria = new Categorias("Livre", "#ffffff");
        Categorias categoriaResponse = categoriasService.salvarCategoria(categoria);
        Assert.assertNotNull(categoriaResponse.getIdCategoria());
        Assert.assertNotNull(categoriaResponse.getNomeCategoria());
        Assert.assertNotNull(categoriaResponse.getCor());
    }

    @Test
    public void updateCategoria(){
        Categorias categoria = new Categorias("Livre", "#ffffff");
        Categorias categoriaResponse = categoriasService.salvarCategoria(categoria);
        categoriaResponse.setNomeCategoria("LivreAtualizada");
        Assert.assertNotNull(categoriasService.salvarCategoria(categoria));
        Assert.assertEquals("LivreAtualizada",categoriasService.salvarCategoria(categoria).getNomeCategoria());
    }

    @Test
    public void deveriaRetornarCategoria(){
        Categorias categoria = new Categorias("Livre", "#ffffff");
        Categorias categoriaResponse = categoriasService.salvarCategoria(categoria);
        Assert.assertNotNull(categoriasService.buscarPorId(categoriaResponse.getIdCategoria()));
    }

    @Test
    public void deveriaRetornarNullSeNaoEncontrarCategoria(){
        Assert.assertEquals(Optional.empty(),categoriasService.buscarPorId(100L));
    }

    @Test
    public void deleteCategoria(){
        Categorias categoria = new Categorias("Livre", "#ffffff");
        Categorias categoriaResponse = categoriasService.salvarCategoria(categoria);
        categoriasService.removerPorId(categoriaResponse.getIdCategoria());
        Assert.assertNotNull(categoriasService.buscarPorId(categoriaResponse.getIdCategoria()));
    }

}
