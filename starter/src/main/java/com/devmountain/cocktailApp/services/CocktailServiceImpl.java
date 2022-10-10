package com.devmountain.cocktailApp.services;

import com.devmountain.cocktailApp.dtos.CocktailDto;
import com.devmountain.cocktailApp.entities.Cocktail;
import com.devmountain.cocktailApp.entities.User;
import com.devmountain.cocktailApp.repositories.CocktailRepository;
import com.devmountain.cocktailApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CocktailServiceImpl implements CocktailService {
    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private UserRepository userRepository;
    // adding cocktail
    @Override
    @Transactional
    public void addCocktail(CocktailDto cocktailDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        Cocktail cocktail = new Cocktail(cocktailDto);
        userOptional.ifPresent(cocktail::setUser);
        cocktailRepository.saveAndFlush(cocktail);
    }

    // deleting cocktail by ID
    @Override
    @Transactional
    public void deleteCocktailById(Long cocktailId){
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(cocktailId);
        cocktailOptional.ifPresent(cocktail-> cocktailRepository.delete(cocktail));
    }

    //updating a cocktail by ID
    @Override
    @Transactional
    public void updateCocktailById(CocktailDto cocktailDto){
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(cocktailDto.getId());
        cocktailOptional.ifPresent(cocktail-> {
            cocktail.setBody(cocktailDto.getBody());
            cocktailRepository.saveAndFlush(cocktail);
        });
    }
    // get all cocktails
    @Override
    public List<CocktailDto> getAllCocktails(){
        List<Cocktail> cocktailList = cocktailRepository.findAll();
        return cocktailList.stream().map(cocktail-> new CocktailDto(cocktail)).collect(Collectors.toList());

    }

    // get cocktail by user id
    @Override
    @Transactional
    public List<CocktailDto> getAllCocktailByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            List<Cocktail> cocktailList = cocktailRepository.findAllByUserEquals(userOptional.get());
            return cocktailList.stream().map(cocktail-> new CocktailDto(cocktail)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    // get cocktail by cocktail id
    @Override
    @Transactional
    public Optional<CocktailDto> getCocktailById(Long cocktailId){
        Optional<Cocktail> cocktailOptional = cocktailRepository.findById(cocktailId);
        if(cocktailOptional.isPresent()){
            return Optional.of(new CocktailDto(cocktailOptional.get()));
        }
        return  Optional.empty();
    }

}
