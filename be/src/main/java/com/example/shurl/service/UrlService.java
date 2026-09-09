package com.example.shurl.service;

import com.example.shurl.model.UrlEntity;
import com.example.shurl.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }

    protected String generateShortCode(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for(int i=0;i<7;i++){
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }

    public String shortenUrl(String originalUrl){
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setOriginalUrl(originalUrl);
        urlEntity.setShortCode(generateShortCode());
        urlEntity.setCreatedAt(LocalDateTime.now());
        urlRepository.save(urlEntity);
        return urlEntity.getShortCode();
    }

    public String getOriginalUrl(String shortCode){
        UrlEntity urlEntity = urlRepository.findByShortCode(shortCode)
                .orElseThrow(()->new RuntimeException("URL NOT FOUND"));
        return urlEntity.getOriginalUrl();
    }

}
