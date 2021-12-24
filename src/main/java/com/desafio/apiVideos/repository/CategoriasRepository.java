package com.desafio.apiVideos.repository;

import com.desafio.apiVideos.Model.Categorias;
import com.desafio.apiVideos.Model.Videos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//Criando um ropository que vai herdar os metodos do jpaRepository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

    Page<Categorias> findByNomeCategoria(String tituloVideo, Pageable paginacao);

}
