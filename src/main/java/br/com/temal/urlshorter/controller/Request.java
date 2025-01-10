package br.com.temal.urlshorter.controller;

import java.net.MalformedURLException;
import java.net.URL;

public record Request(String originalUrl) {

    public Request {
        try {
            new URL(originalUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("originalUrl is not a valid url");
        }
    }
}
