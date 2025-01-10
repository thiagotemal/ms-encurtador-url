package br.com.temal.urlshorter.controller;


import br.com.temal.urlshorter.service.ShorterService;
import br.com.temal.urlshorter.entity.UrlEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/urls")

public class ShorterUrlController {
    private final ShorterService service;

    public ShorterUrlController(ShorterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response> shorten(@RequestBody Request request, HttpServletRequest httpServletRequest) {
        UrlEntity urlEntity = service.shorter(request.originalUrl());
        var urlFormated = httpServletRequest.getRequestURL().toString().concat("/").concat(urlEntity.getShortUrl());
        Response response = new Response(request.originalUrl(), urlFormated);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String shortUrl) {
        var urlEntity = service.findByShortUrl(shortUrl);
        if (urlEntity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).headers(buildResponseHttpHeaders(urlEntity)).build();
    }

    private static HttpHeaders buildResponseHttpHeaders(UrlEntity urlEntity) {
        String location = "Location";
        HttpHeaders headers = new HttpHeaders();
        var originalUrl = urlEntity.getOriginalUrl();

        headers.add(location, originalUrl);
        return headers;
    }


}
