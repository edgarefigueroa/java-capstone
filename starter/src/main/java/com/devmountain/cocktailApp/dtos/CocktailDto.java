package com.devmountain.cocktailApp.dtos;


import com.devmountain.cocktailApp.entities.Cocktail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CocktailDto implements Serializable {
    private Long id;
    private String cocktailName;
    private String cocktailType;
    private String body;

    public CocktailDto(Cocktail cocktail){
        if (cocktail.getId() != null){
            this.id = cocktail.getId();
        }
        if (cocktail.getCocktailName() != null){
            this.cocktailName = cocktail.getCocktailName();
        }
        if (cocktail.getCocktailType() != null){
            this.cocktailType = cocktail.getCocktailType();
        }
        if (cocktail.getBody() != null){
            this.body = cocktail.getBody();
        }
    }
}
