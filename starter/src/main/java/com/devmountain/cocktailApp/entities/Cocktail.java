package com.devmountain.cocktailApp.entities;

import com.devmountain.cocktailApp.dtos.CocktailDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cocktails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "cocktail_name")
    private String cocktailName;

    @Column(name = "cocktail_type")
    private String cocktailType;
    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<Favorite> favorites;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getCocktailType() {
        return cocktailType;
    }

    public void setCocktailType(String cocktailType) {
        this.cocktailType = cocktailType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Favorite> getFavorites()
    {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites)
    {
        this.favorites = favorites;
    }

    public void addFavorite(Favorite favorite)
    {
        this.favorites.add(favorite);
    }

//    public Cocktail() {
//    }

    public Cocktail(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public Cocktail(CocktailDto cocktailDto) {
        if(cocktailDto.getCocktailName() != null){
            this.cocktailName = cocktailDto.getCocktailName();
        }
        if (cocktailDto.getCocktailType() != null){
            this.cocktailType = cocktailDto.getCocktailType();
        }
        if (cocktailDto.getBody() != null) {
            this.body = cocktailDto.getBody();
        }

    }

}
