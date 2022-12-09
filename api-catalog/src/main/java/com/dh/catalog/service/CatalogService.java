package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final MovieServiceClient movieServiceClient;

    public CatalogService(MovieServiceClient movieServiceClient) {
        this.movieServiceClient = movieServiceClient;
    }

    @CircuitBreaker(name = "clientMovie", fallbackMethod = "callMovieFallBack")
    @Retry(name = "clientMovie")
    public List<MovieServiceClient.MovieDto> getMovieByGenre(String genre) throws Exception {
        List movies = movieServiceClient.getMovieByGenre(genre);
        if (movies.isEmpty()) {

                throw new Exception("No hay peliculas para el genero seleccionado");
            }

        else{
            throw new Exception("Género not found");
        }
    }

    public String callMovieFallBack(CallNotPermittedException exception) {
        return exception.getMessage();

    }

}
