package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}