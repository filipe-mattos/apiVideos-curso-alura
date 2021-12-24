package com.desafio.apiVideos.controller;

import com.desafio.apiVideos.Model.Categorias;
import com.desafio.apiVideos.Model.Videos;
import com.desafio.apiVideos.repository.CategoriasRepository;
import com.desafio.apiVideos.service.CategoriasService;
import com.desafio.apiVideos.service.VideosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @Autowired
    private VideosService videosService;
    
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Categorias> listarCategorias(@RequestParam(required = false) String nomeCategoria,
                                             @PageableDefault(page=0, size = 5) Pageable paginacao){

        if(nomeCategoria == null){
            return categoriasRepository.findAll(paginacao);
        }else{
            return categoriasRepository.findByNomeCategoria(nomeCategoria, paginacao);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categorias listarCategoria(@PathVariable("id") Long id){
        return categoriasService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria Não Encontrada"));
    }

    @GetMapping("{id}/videos")//rota que ira mostrar os videos de uma categoria
    @ResponseStatus(HttpStatus.OK)
    public List<Videos> listaVideosDaCategoria(@PathVariable("id") Long id){
        System.out.println(id);
        return videosService.buscarPorCategoria(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Categorias salvarCategoria(@RequestBody Categorias categoria){
        return categoriasService.salvarCategoria(categoria);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Categorias> atualizarCategoria(@PathVariable("id") Long id, @RequestBody Categorias categoria){
        categoriasService.buscarPorId(id)
                .map(categoriaSelecionada -> {
                    modelMapper.map(categoria, categoriaSelecionada);//Utilizando o modelmapper para pegar todas as infos passadas no json e atualizar no video da base de dados
                    return categoriasService.salvarCategoria(categoriaSelecionada);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
        return categoriasService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id){
        categoriasService.buscarPorId(id).map(categoria -> {
            categoriasService.removerPorId(categoria.getIdCategoria());
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video não encontrado"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
