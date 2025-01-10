package br.com.temal.urlshorter.service;

import br.com.temal.urlshorter.entity.UrlEntity;
import br.com.temal.urlshorter.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service

public class ShorterService {
    private final UrlRepository repository;

    @Value("${urlshorter.timeToExpireInMinutes:5}")
    private int timeToExpireInMinutes;

    public ShorterService(UrlRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UrlEntity shorter(String originalUrl) {

        String chave;
        do {
            chave = RandomStringUtils.randomAlphanumeric(7, 7);

        } while (repository.findById(originalUrl).isPresent());
        UrlEntity urlToSave =  new UrlEntity(originalUrl,chave, LocalDateTime.now().plusMinutes(timeToExpireInMinutes));

      return  repository.save(urlToSave);

    }

    public UrlEntity findByShortUrl(String shortUrl) {
        return repository.findById(shortUrl).orElse(null);
    }


}
