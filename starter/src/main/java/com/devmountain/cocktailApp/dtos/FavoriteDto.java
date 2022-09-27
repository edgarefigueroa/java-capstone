package com.devmountain.cocktailApp.dtos;

import com.devmountain.cocktailApp.entities.Cocktail;
import com.devmountain.cocktailApp.entities.Favorite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {
    private Long id;
    private String favoriteName;

    public FavoriteDto(Favorite favorite){
        if (favorite.getId() != null){
            this.id = favorite.getId();
        }
        if (favorite.getFavoriteName() != null){
            this.favoriteName = favorite.getFavoriteName();
        }
    }
}
