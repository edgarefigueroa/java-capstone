package com.devmountain.cocktailApp.services;

import com.devmountain.cocktailApp.dtos.CocktailDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CocktailService {
    // adding cocktail
    @Transactional
    void addCocktail(CocktailDto noteDto, Long userId);

    // deleting cocktail by ID
    @Transactional
    void deleteCocktailById(Long cocktailId);

    //updating a note by ID
    @Transactional
    void updateCocktailById(CocktailDto cocktailDto);

    // get cocktail by user id
    @Transactional
    List<CocktailDto> getAllCocktailByUserId(Long userId);

    // get cocktail by cocktail id
    @Transactional
    Optional<CocktailDto> getCocktailById(Long cocktailId);
}
