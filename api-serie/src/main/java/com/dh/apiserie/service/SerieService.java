package com.dh.apiserie.service;


import com.dh.apiserie.model.SerieEntity;
import com.dh.apiserie.repository.ISerieRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {


    private final ISerieRepository serieRepository;


    public SerieService(ISerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void save(SerieEntity serie){
        serieRepository.save(serie);
    }

    public List<SerieEntity> getByGenre(String genre){
        List<SerieEntity> series = serieRepository.findByGenre(genre);
        return series;

    }
}
