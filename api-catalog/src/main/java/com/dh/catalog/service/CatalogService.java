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
    public ResponseEntity<List> getMovieByGenre(String genre) throws Exception {
        ResponseEntity<List> response = (ResponseEntity<List>) movieServiceClient.getMovieByGenre(genre);
        //List<MovieServiceClient.MovieDto> movies = movieServiceClient.getMovieByGenre(genre);
        if (response.getBody().isEmpty()) {

                throw new Exception("No hay peliculas para el genero seleccionado");
            }

        else return response;
    }

    public ResponseEntity<?> callMovieFallBack(String genre, CallNotPermittedException exception) throws CircuitBreakerException {
        return new ResponseEntity<>("El servicio no está disponible", HttpStatus.FORBIDDEN);

    }

}

/*@GetMapping("/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(movieServiceClient.getMovieByGenre(genre));
	}*/
