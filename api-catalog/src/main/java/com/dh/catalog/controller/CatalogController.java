package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {


	private final MovieServiceClient movieServiceClient;
	private final SerieServiceClient serieServiceClient;
	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService, MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient){
		this.catalogService = catalogService;
		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
	}

	//@GetMapping("/{genre}")
	@GetMapping("/movies/{genre}")

	ResponseEntity<List<MovieServiceClient.MovieDto>> getMovieGenre(@PathVariable String genre) throws Exception {
		return ResponseEntity.ok().body(catalogService.getMovieByGenre(genre));

	}

	@GetMapping("/series/{genre}")

	ResponseEntity<List<SerieServiceClient.SerieDTO>> getSerieGenre(@PathVariable String genre) throws Exception {
		return ResponseEntity.ok().body(serieServiceClient.getSerieByGenre(genre));
	}

}
