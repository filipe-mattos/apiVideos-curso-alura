package com.desafio.apiVideos.repository;

import com.desafio.apiVideos.Model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

//Criando um ropository que vai herdar os metodos do jpaRepository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
}
