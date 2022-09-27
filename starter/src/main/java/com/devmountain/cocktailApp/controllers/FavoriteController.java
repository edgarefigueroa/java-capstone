package com.devmountain.cocktailApp.controllers;

import com.devmountain.cocktailApp.dtos.FavoriteDto;
import com.devmountain.cocktailApp.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/user/{userId}")
    public List<FavoriteDto> getFavoritesByUser(@PathVariable Long userId){
        return favoriteService.getAllFavoriteByUserId(userId);
    }

    @GetMapping("/{favoriteId}")
    public Optional<FavoriteDto> getFavoriteById(@PathVariable Long favoriteId){
        return  favoriteService.getFavoriteById(favoriteId);
    }

    @PostMapping("/user/{userId}")
    public void addFavorite(@RequestBody FavoriteDto favoriteDto, @PathVariable Long userId){
        favoriteService.addFavorite(favoriteDto,userId);
    }
}
