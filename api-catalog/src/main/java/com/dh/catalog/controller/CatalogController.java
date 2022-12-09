package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;
	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService, MovieServiceClient movieServiceClient){
		this.catalogService = catalogService;
		this.movieServiceClient = movieServiceClient;
	}

	@GetMapping("/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getGenre(@PathVariable String genre) throws Exception {
		return ResponseEntity.ok(catalogService.getMovieByGenre(genre));
	}

}
