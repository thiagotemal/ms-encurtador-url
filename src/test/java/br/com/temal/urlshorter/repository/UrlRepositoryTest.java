package br.com.temal.urlshorter.repository;

import br.com.temal.urlshorter.AbstractBaseIntegrationTest;
import br.com.temal.urlshorter.entity.UrlEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


class UrlRepositoryTest extends AbstractBaseIntegrationTest {


    @Autowired
    private UrlRepository urlRepository;


    @Test
    void shouldSaveUrlEntityCorrectly() {
        UrlEntity url = new UrlEntity();
        url.setOriginalUrl("https://www.example.com");
        url.setShortUrl("https://short.url");
        url.setExpirationDate(LocalDateTime.now().plusMinutes(1));

        // Arrange
        var urlSaved = urlRepository.save(url);
        // Act
        var result = urlRepository.findById(urlSaved.getShortUrl());

        // Assert
        Assertions.assertTrue(result.isPresent());
        UrlEntity resultUrlEntity = result.get();
        assertEquals(url.getOriginalUrl(), resultUrlEntity.getOriginalUrl());
        assertEquals(url.getShortUrl(), resultUrlEntity.getShortUrl());

    }
}
