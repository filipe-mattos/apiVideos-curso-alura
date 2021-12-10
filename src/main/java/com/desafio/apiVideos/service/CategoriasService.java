package com.desafio.apiVideos.service;

import com.desafio.apiVideos.Model.Categorias;
import com.desafio.apiVideos.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public Categorias salvarCategoria(Categorias categoria){return categoriasRepository.save(categoria);}

    public List<Categorias> listarCategorias(){
        return categoriasRepository.findAll();
    }

    public Optional<Categorias> buscarPorId(Long id){
        return categoriasRepository.findById(id);
    }

    public void removerPorId(Long id){
        categoriasRepository.deleteById(id);
    }

}
