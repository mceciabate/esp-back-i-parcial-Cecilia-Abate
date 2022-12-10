package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.handler.CircuitBreakerException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MovieServiceClient.MovieDto>> getMovieByGenre(String genre) throws Exception {
        List movies = movieServiceClient.getMovieByGenre(genre);
        if (movies.isEmpty()) {

                throw new Exception("No hay peliculas para el genero seleccionado");
            }

        else return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> callMovieFallBack(String genre, CallNotPermittedException exception) throws CircuitBreakerException {
        return new ResponseEntity<>("El servicio no está disponible", HttpStatus.FORBIDDEN);

    }

}