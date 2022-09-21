package com.devmountain.cocktailApp.controllers;

import com.devmountain.cocktailApp.dtos.CocktailDto;
import com.devmountain.cocktailApp.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cocktails")
public class CocktailController {
    @Autowired
    private CocktailService cocktailService;

    @GetMapping("/user/{userId}")
    public List<CocktailDto> getNotesByUser(@PathVariable Long userId){
        return cocktailService.getAllCocktailByUserId(userId);
    }

    @GetMapping("/{cocktailId}")
    public Optional<CocktailDto> getCocktailById(@PathVariable Long cocktailId){
        return  cocktailService.getCocktailById(cocktailId);
    }

    @PostMapping("/user/{userId}")
    public void addCocktail(@RequestBody CocktailDto cocktailDto, @PathVariable Long userId){
        cocktailService.addCocktail(cocktailDto,userId);
    }

    @DeleteMapping("/{cocktailId}")
    public void deleteCocktailById(@PathVariable Long cocktailId){
        cocktailService.deleteCocktailById(cocktailId);
    }

    @PutMapping
    public void updateNote(@RequestBody CocktailDto noteDto){
        cocktailService.updateCocktailById(noteDto);
    }
}
