package com.devmountain.cocktailApp.repositories;

import com.devmountain.cocktailApp.entities.Favorite;
import com.devmountain.cocktailApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByUserEquals(User user);
}
