package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
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
    @Retry(name = "clientmovie")
    public void getMovieByGenre(String genre) throws Exception {
        List movies = movieServiceClient.getMovieByGenre(genre);
        if (movies.isEmpty()) {

                throw new Exception("No hay peliculas para el genero seleccionado");
            }

        else{
            throw new Exception("Género not found");
        }
    }

    public void callMovieFallBack(String genre, Throwable t) throws Exception {
        System.out.println("El servicio no está disponible en este momento");

    }

}
