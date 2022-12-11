package com.dh.apiserie.service;


import com.dh.apiserie.event.NewSerieEventProducer;
import com.dh.apiserie.model.SerieEntity;
import com.dh.apiserie.repository.ISerieRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {


    private final ISerieRepository serieRepository;
    private final NewSerieEventProducer newSerieEventProducer;


    public SerieService(ISerieRepository serieRepository, NewSerieEventProducer newSerieEventProducer) {
        this.serieRepository = serieRepository;
        this.newSerieEventProducer = newSerieEventProducer;
    }

    public SerieEntity save(SerieEntity serie){
        serieRepository.save(serie);
        newSerieEventProducer.execute(serie);
        return serie;
    }

    public List<SerieEntity> getByGenre(String genre){
        List<SerieEntity> series = serieRepository.findByGenre(genre);
        return series;

    }
}
