package br.com.temal.urlshorter.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;



@Document("urls")
public class UrlEntity {

    private String originalUrl;
    @MongoId
    private String shortUrl;

    @Indexed(expireAfter = "0")
    private LocalDateTime expirationDate;

    public UrlEntity() {
    }

    public UrlEntity(String originalUrl, String shortUrl, LocalDateTime expirationDate) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.expirationDate = expirationDate;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }


}
