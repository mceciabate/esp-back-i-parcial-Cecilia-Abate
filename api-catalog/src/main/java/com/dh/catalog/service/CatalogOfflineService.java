package com.dh.catalog.service;

import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.repository.MovieMongoRepository;

import java.util.List;

public class CatalogOfflineService {

    private final MovieMongoRepository movieMongoRepository;


    public CatalogOfflineService(MovieMongoRepository movieMongoRepository) {
        this.movieMongoRepository = movieMongoRepository;
    }

    public List<MovieEntity> movieGenreOffline(String genre){
        return movieMongoRepository.findByGenre(genre);
    }
}
