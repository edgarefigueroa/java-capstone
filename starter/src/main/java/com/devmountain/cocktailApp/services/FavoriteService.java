package com.devmountain.cocktailApp.services;

import com.devmountain.cocktailApp.dtos.FavoriteDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    @Transactional
    void addFavorite(FavoriteDto favoriteDto, Long userId);

    @Transactional
    List<FavoriteDto> getAllFavoriteByUserId(Long userId);

    @Transactional
    Optional<FavoriteDto> getFavoriteById(Long favoriteId);
}
