package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;
	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService, MovieServiceClient movieServiceClient){
		this.catalogService = catalogService;
		this.movieServiceClient = movieServiceClient;
	}
//@RequestMapping(value = "/test", method = RequestMethod.POST)
	@RequestMapping(value = "/{genre}", method = RequestMethod.GET)
	ResponseEntity<?> getGenre(@PathVariable String genre) throws Exception {
		return ResponseEntity.ok().body(MovieServiceClient.MovieDto.class);
	}

}
