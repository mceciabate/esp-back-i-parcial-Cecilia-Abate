package com.dh.apiserie.repository;

import com.dh.apiserie.model.SerieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISerieRepository extends MongoRepository<SerieEntity, Long> {

    List<SerieEntity> findByGenre(String genre);



}
