package com.dh.apiserie;

import com.dh.apiserie.model.ChapterEntity;
import com.dh.apiserie.model.SeasonEntity;
import com.dh.apiserie.model.SerieEntity;
import com.dh.apiserie.repository.ISerieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class ApiSerieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSerieApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(ISerieRepository repository){
		return (args) -> {
			if (!repository.findAll().isEmpty()){
				return;
			}
			ChapterEntity chapter1 = new ChapterEntity(1L, "el episodio 1", 1, "www.crunchyroll.com");
			ChapterEntity chapter2 = new ChapterEntity(2L, "el episodio 2", 2, "www.crunchyroll.com");
			ChapterEntity chapter3 = new ChapterEntity(3L, "el episodio 3", 3, "www.crunchyroll.com");
			ArrayList<ChapterEntity> capitulosLista1 = new ArrayList<>();
			capitulosLista1.add(chapter1);
			capitulosLista1.add(chapter2);
			capitulosLista1.add(chapter3);
			SeasonEntity season1 = new SeasonEntity(1L, 1, capitulosLista1);
			SeasonEntity season2 = new SeasonEntity(2L, 2, capitulosLista1);
			ArrayList<SeasonEntity> seasonLista1 = new ArrayList<>();
			seasonLista1.add(season1);
			seasonLista1.add(season2);
			SerieEntity serie1 = new SerieEntity(1L, "la serie", "drama", seasonLista1);
			SerieEntity serie2 = new SerieEntity(2L, "otra serie 2", "drama", seasonLista1);
			SerieEntity serie3 = new SerieEntity(3L, "otra serie 3", "comedia", seasonLista1);
			SerieEntity serie4 = new SerieEntity(4L, "otra serie 4", "comedia", seasonLista1);
			SerieEntity serie5 = new SerieEntity(5L, "otra serie 5", "infantil", seasonLista1);
			SerieEntity serie6 = new SerieEntity(6L, "otra serie 6", "infantil", seasonLista1);
			SerieEntity serie7 = new SerieEntity(7L, "otra serie 7", "accion", seasonLista1);

			repository.save(serie1);
			repository.save(serie2);
			repository.save(serie3);
			repository.save(serie4);
			repository.save(serie5);
			repository.save(serie6);
			repository.save(serie7);
		};
	}

}
