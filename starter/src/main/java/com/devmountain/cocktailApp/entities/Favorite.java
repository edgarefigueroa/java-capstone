package com.devmountain.cocktailApp.entities;

import com.devmountain.cocktailApp.dtos.FavoriteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Favorites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "favorite_Name")
    private String favoriteName;

//    @OneToMany(mappedBy = "favorite", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonManagedReference
//    private Set<Cocktail> cocktail = new HashSet<>();
//
//    @OneToMany(mappedBy = "favorite", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonManagedReference
//    private Set<User> user = new HashSet<>();

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private List<User> favorites;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Favorite() {
//    }

    public Favorite(Long id) {
        this.id = id;

    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public Favorite(Long id, String favoriteName) {
        this.id = id;
        this.favoriteName = favoriteName;
    }

    public Favorite(FavoriteDto favoriteDto) {
        if (favoriteDto.getFavoriteName() != null) {
            this.favoriteName = favoriteDto.getFavoriteName();
        }
    }

//    public void setUser(User user) {
//    }
}
