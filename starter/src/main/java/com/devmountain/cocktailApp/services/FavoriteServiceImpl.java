package com.devmountain.cocktailApp.services;

import com.devmountain.cocktailApp.dtos.CocktailDto;
import com.devmountain.cocktailApp.dtos.FavoriteDto;
import com.devmountain.cocktailApp.entities.Cocktail;
import com.devmountain.cocktailApp.entities.Favorite;
import com.devmountain.cocktailApp.entities.User;
import com.devmountain.cocktailApp.repositories.FavoriteRepository;
import com.devmountain.cocktailApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addFavorite(FavoriteDto favoriteDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        Favorite favorite = new Favorite(favoriteDto);
        userOptional.ifPresent(favorite::setUser);
        favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    @Transactional
    public List<FavoriteDto> getAllFavoriteByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            List<Favorite> favoriteList = favoriteRepository.findAllByUserEquals(userOptional.get());
            return favoriteList.stream().map(favorite-> new FavoriteDto(favorite)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Optional<FavoriteDto> getFavoriteById(Long favoriteId){
        Optional<Favorite> favoriteOptional = favoriteRepository.findById(favoriteId);
        if(favoriteOptional.isPresent()){
            return Optional.of(new FavoriteDto(favoriteOptional.get()));
        }
        return  Optional.empty();
    }

}
