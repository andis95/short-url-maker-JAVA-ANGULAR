package com.example.shurl.controller;

import com.example.shurl.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService=urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl){
        String shortCode = urlService.shortenUrl(originalUrl);
        String shortUrl = "http://localhost:8080/r/"+shortCode;
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<String> redirect(@PathVariable String shortCode){
        String originalUrl= urlService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
