package com.dh.catalog.service;

import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.model.SerieEntity;
import com.dh.catalog.repository.MovieMongoRepository;
import com.dh.catalog.repository.SerieMongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogOfflineService {

    private final MovieMongoRepository movieMongoRepository;

    private final SerieMongoRepository serieMongoRepository;


    public CatalogOfflineService(MovieMongoRepository movieMongoRepository, SerieMongoRepository serieMongoRepository) {
        this.movieMongoRepository = movieMongoRepository;
        this.serieMongoRepository = serieMongoRepository;
    }

    public List<MovieEntity> findMovieByGenre(String genre) {
        List<MovieEntity> allMovies = movieMongoRepository.findAll();
        List<MovieEntity> genreMovies = new ArrayList<>();
        for (MovieEntity movie : allMovies) {
            if (movie.getGenre().equals(genre)) {
                genreMovies.add(movie);
            }
        }
        return genreMovies;
    }

    public List<SerieEntity> findSerieByGenre(String genre) {
        List<SerieEntity> allSeries = serieMongoRepository.findAll();
        List<SerieEntity> genreSeries = new ArrayList<>();
        for (SerieEntity serie : allSeries) {
            if (serie.getGenre().equals(genre)) {
                genreSeries.add(serie);
            }
        }
        return genreSeries;


    }
}
