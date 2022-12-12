package com.dh.apiserie.controller;

import com.dh.apiserie.model.SerieEntity;
import com.dh.apiserie.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<SerieEntity>> getSeriesByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(serieService.getByGenre(genre));
    }

    @PostMapping("/save")
    public ResponseEntity<SerieEntity> saveSerie(@RequestBody SerieEntity serie){
        return ResponseEntity.ok().body(serieService.save(serie));
    }

    @GetMapping()
    public ResponseEntity<List<SerieEntity>> getAll(){
        return ResponseEntity.ok(serieService.getAllSeries());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSerie(@PathVariable Long id){
        serieService.deleteSerie(id);
        return ResponseEntity.ok().build();
    }




}
