package com.dh.catalog.service;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.handler.ApiException;
import org.apache.log4j.Logger;
import com.dh.catalog.client.MovieServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class CatalogService {

    final static Logger log = Logger.getLogger(CatalogService.class);
    private final MovieServiceClient movieServiceClient;

    private final SerieServiceClient serieServiceClient;

    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }

    //APLICO EL PATRON CIRCUIT BREAKER PARA LOS MÉTODOS QUE SON CONSUMIDOS DE FORMA ONLINE A TRAVÉS DEL FEINGCLIENT
    //AMBOS MÉTODOS TIENEN ESTABLECIDO UN RETRY PARA CUANDO LA CONEXIÓN INTENTE SER REESTABLECIDA CON EL MS QUE ES CONSUMIDO
    //LAS REGLAS ESTABLECIDAS PARA EL COMPORTAMIENTO DE RESILENCE4J SE ENCUENTRAN EN API-CATALOG.YML PARA CADA UNA DE LAS INSTANCIAS
    @CircuitBreaker(name = "clientMovie", fallbackMethod = "callMovieFallBack")
    @Retry(name = "clientMovie")
    public List<?> getMovieByGenre(String genre) {
        ApiException error;
        List response =  movieServiceClient.getMovieByGenre(genre);
        if (response.isEmpty()) {
            error = new ApiException("No hay peliculas para el género seleccionado", HttpStatus.NOT_FOUND, ZonedDateTime.now());
            response.add(error);
        }

        else log.info("Consultando las peliculas del género " + genre);
        return response;
    }

    public List<?> callMovieFallBack(String genre, Throwable t) {
        ApiException error;
        List response =  movieServiceClient.getMovieByGenre(genre);
        if (response.isEmpty()) {
            error = new ApiException("No hay peliculas para el género seleccionado", HttpStatus.NOT_FOUND, ZonedDateTime.now());
            response.add(error);
        }

        else log.info("Retry de Movies");
        return response;
    }




    @CircuitBreaker(name= "clientSerie", fallbackMethod = "callSerieFallBack")
    @Retry(name = "clientSerie")
    public List<?> getSerieByGenre(String genre) {
        ApiException error;
        List response = serieServiceClient.getSerieByGenre(genre);
        if (response.isEmpty()) {
            error = new ApiException("No hay series para el género seleccionado", HttpStatus.NOT_FOUND, ZonedDateTime.now());
            response.add(error);
        }
        log.info("Consultando las series del género " + genre);
        return response;
    }
    public List<?> callSerieFallBack(String genre, Throwable t) {
        ApiException error;
        List response = serieServiceClient.getSerieByGenre(genre);
        if (response.isEmpty()) {
            error = new ApiException("No hay series para el genero seleccionado", HttpStatus.NOT_FOUND, ZonedDateTime.now());
            response.add(error);
        }
        log.info("Retry de Series");
        return response;
    }




}
