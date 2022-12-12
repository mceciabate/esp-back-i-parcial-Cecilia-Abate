package com.dh.catalog.service;
import com.dh.catalog.client.SerieServiceClient;
import org.apache.log4j.Logger;
import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.handler.CircuitBreakerException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    final static Logger log = Logger.getLogger(CatalogService.class);
    private final MovieServiceClient movieServiceClient;

    private final SerieServiceClient serieServiceClient;

    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }


    @CircuitBreaker(name = "clientMovie", fallbackMethod = "callMovieFallBack")
    @Retry(name = "clientMovie")
    public List<MovieServiceClient.MovieDto> getMovieByGenre(String genre) throws CircuitBreakerException {
        List response =  movieServiceClient.getMovieByGenre(genre);
        //List<MovieServiceClient.MovieDto> movies = movieServiceClient.getMovieByGenre(genre);
        if (response.isEmpty()) {

                throw new CircuitBreakerException("No hay peliculas para el genero seleccionado");
            }

        else log.info("Consultando las peliculas del género " + genre);
        return response;
    }

    public List<?> callMovieFallBack(String genre, CallNotPermittedException exception) {
        List error = new ArrayList<>();
        error.add(exception);
        error.add(genre);
        return error;
    }

    @CircuitBreaker(name= "clientSerie")
    @Retry(name = "clientSerie")
    public List<SerieServiceClient.SerieDTO> getSerieByGenre(String genre) throws CircuitBreakerException {
        List response = serieServiceClient.getSerieByGenre(genre);
        if (response.isEmpty()) {

            throw new CircuitBreakerException("No hay series para el genero seleccionado");
        }

        else log.info("Consultando las series del género " + genre);
        return response;
    }
//    public List<Optional> callSerieFallBack(String genre, CallNotPermittedException exception) {
//        List error = new ArrayList<>();
//        error.add(exception);
//        error.add(genre);
//        return error;
//    }


}
