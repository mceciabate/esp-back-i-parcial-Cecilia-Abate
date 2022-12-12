package com.dh.apiserie.service;


import com.dh.apiserie.event.NewSerieEventProducer;
import com.dh.apiserie.model.SerieEntity;
import com.dh.apiserie.repository.ISerieRepository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    final static Logger log = Logger.getLogger(SerieService.class);
    private final ISerieRepository serieRepository;
    private final NewSerieEventProducer newSerieEventProducer;


    public SerieService(ISerieRepository serieRepository, NewSerieEventProducer newSerieEventProducer) {
        this.serieRepository = serieRepository;
        this.newSerieEventProducer = newSerieEventProducer;
    }

    public SerieEntity save(SerieEntity serie){
        serieRepository.save(serie);
        newSerieEventProducer.execute(serie);
        log.info("Guardando la serie " + serie.getName());
        return serie;
    }

    public List<SerieEntity> getByGenre(String genre){
        List<SerieEntity> series = serieRepository.findByGenre(genre);
        log.info("Buscando las peliculas del género " + genre);
        return series;

    }

    public List<SerieEntity> getAllSeries(){
        List<SerieEntity> allSeries = serieRepository.findAll();
        log.info("Listado completo de series");
        return allSeries;
    }

    public void deleteSerie(Long id){
        log.info("Eliminando la pelicula con el id " + id);
        serieRepository.deleteById(id);
    }
}
