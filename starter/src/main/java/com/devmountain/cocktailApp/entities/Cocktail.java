package com.devmountain.cocktailApp.entities;

import com.devmountain.cocktailApp.dtos.CocktailDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Cocktails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

//    public Cocktail() {
//    }

    public Cocktail(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public Cocktail(CocktailDto cocktailDto) {
        if (cocktailDto.getBody() != null) {
            this.body = cocktailDto.getBody();
        }
    }

}
