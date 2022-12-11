package com.dh.catalog.service;

import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.repository.MovieMongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogOfflineService {

    private final MovieMongoRepository movieMongoRepository;


    public CatalogOfflineService(MovieMongoRepository movieMongoRepository) {
        this.movieMongoRepository = movieMongoRepository;
    }

    public List<MovieEntity> findMovieByGenre(String genre){
        List<MovieEntity> allMovies = movieMongoRepository.findAll();
        List<MovieEntity> genreMovies = new ArrayList<>();
        for (MovieEntity allMovie : allMovies) {
            if (allMovie.getGenre().equals(genre)){
                genreMovies.add(allMovie);
            }
        }return genreMovies;
    }


}
