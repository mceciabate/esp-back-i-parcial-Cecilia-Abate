package com.dh.catalog.repository;


import com.dh.catalog.model.ChapterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterMongoRepository extends MongoRepository<ChapterEntity, Long> {
}
