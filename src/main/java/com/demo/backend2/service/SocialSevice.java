package com.demo.backend2.service;

import java.util.Optional;

import com.demo.backend2.entity.Social;
import com.demo.backend2.entity.User;
import com.demo.backend2.repository.SocialRepository;

import org.springframework.stereotype.Service;

@Service
public class SocialSevice {

    private final SocialRepository repository;
    
    public SocialSevice(SocialRepository repository) {
        this.repository = repository;
    }

    public Optional<Social> findByUser(User user){
        return repository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok){
        //TODO: validate

        //create
        Social entity = new Social();
        
        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);



        return repository.save(entity);
    }
    
}
