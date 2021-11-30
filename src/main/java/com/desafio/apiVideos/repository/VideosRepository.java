package com.desafio.apiVideos.repository;

import com.desafio.apiVideos.Model.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

//Criando um ropository que vai herdar os metodos do jpaRepository
public interface VideosRepository extends JpaRepository<Videos, Long> {

}
