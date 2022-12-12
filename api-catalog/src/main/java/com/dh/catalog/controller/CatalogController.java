package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.model.SerieEntity;
import com.dh.catalog.service.CatalogOfflineService;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {


	private final MovieServiceClient movieServiceClient;
	private final SerieServiceClient serieServiceClient;
	private final CatalogService catalogService;

	private final CatalogOfflineService catalogOfflineService;

	public CatalogController(CatalogService catalogService, MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, CatalogOfflineService catalogOfflineService){
		this.catalogService = catalogService;
		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
		this.catalogOfflineService = catalogOfflineService;
	}

	//@GetMapping("/{genre}")
	@GetMapping("/movies/{genre}")

	ResponseEntity<List<MovieServiceClient.MovieDto>> getMovieGenre(@PathVariable String genre) throws Exception {
		return ResponseEntity.ok().body(catalogService.getMovieByGenre(genre));

	}

//	@GetMapping("/series/{genre}")
//
//	ResponseEntity<List<SerieServiceClient.SerieDTO>> getSerieGenre(@PathVariable String genre) throws Exception {
//		return ResponseEntity.ok().body(serieServiceClient.getSerieByGenre(genre));
//	}


	@GetMapping("/series/{genre}")

	ResponseEntity<List<SerieServiceClient.SerieDTO>> getSerieGenre(@PathVariable String genre) throws Exception {
		return ResponseEntity.ok().body(catalogService.getSerieByGenre(genre));
	}

	@GetMapping("/offline/movies/{genre}")
	ResponseEntity<List<MovieEntity>> getMoviesGenreOffline(@PathVariable String genre){
		return ResponseEntity.ok().body(catalogOfflineService.findMovieByGenre(genre));
	}

	@GetMapping("/offline/series/{genre}")
	ResponseEntity<List<SerieEntity>> getSeriesOffine(@PathVariable String genre){
		return ResponseEntity.ok().body(catalogOfflineService.findSerieByGenre(genre));
	}

}
