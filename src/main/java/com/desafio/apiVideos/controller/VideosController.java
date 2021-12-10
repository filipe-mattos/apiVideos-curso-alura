package com.desafio.apiVideos.controller;

import com.desafio.apiVideos.Model.Categorias;
import com.desafio.apiVideos.Model.Videos;
import com.desafio.apiVideos.service.CategoriasService;
import com.desafio.apiVideos.service.VideosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController//Informando o spring que essa classe sera um controller
@RequestMapping("/videos")//Definindo a rota que esse controller ira seguir
public class VideosController {

    @Autowired//Injetando a varialvel de service no controller
    private VideosService videosService;

    @Autowired
    private CategoriasService categoriasService;

    @Autowired//Injetando a variavel modelMapper no controller
    private ModelMapper modelMapper;
    //Fazer conexão com o oracle db

    //Caso a request seja um get ira retornar uma lista com todos os videos na base de dados
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Videos> listarVideos(){
        return videosService.listarVideos();
    }

    //Caso a request seja um get e possua um id sera retornado o video com o mesmo id passado
    //Caso o video n exista ira lançar um exeption indicando que o video nao foi encontrado
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Videos listarVideo(@PathVariable("id") Long id){

        return videosService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Video Não Encontrado"));
    }

    //Caso a request seja um post ira incluir um vido na base de dados
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//Caso seja incluido com sucesso ira retornar o status correspontente
    //Utilizando o request body para indicar que ira receber um json com os dados
    public Optional<Videos> incluirVideo(@RequestBody Videos video){
        videosService.salvar(video);
        return videosService.buscarPorId(video.getIdVideos());
    }

    //Caso a request seja um delete passando o id do video ira checar se esse video esta presente na base de dados
    //Se estiver ira deletar o mesmo da base, caso n esteja ira retornar uma exception de not found
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removerVideo(@PathVariable("id") Long id){
        videosService.buscarPorId(id).map(video -> {
            videosService.removerPorId(video.getIdVideos());
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video não encontrado"));
    }

    //Caso a request seja um put com um id de parametro ira buscar na base de dados o video pelo id caso ele seja encontrado
    //ira atualizar os dados do video presente na base de dados pelos dados passados no json
    //caso n esteja ira retornar uma exception de not found
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarVideo(@PathVariable("id") Long id, @RequestBody Videos video){
        videosService.buscarPorId(id)
                .map(videoInserido -> {
                    modelMapper.map(video, videoInserido);//Utilizando o modelmapper para pegar todas as infos passadas no json e atualizar no video da base de dados
                    videosService.salvar(videoInserido);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video não encontrado"));
    }

}
