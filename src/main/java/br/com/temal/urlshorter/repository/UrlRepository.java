package br.com.temal.urlshorter.repository;

import br.com.temal.urlshorter.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity,String> {
}
