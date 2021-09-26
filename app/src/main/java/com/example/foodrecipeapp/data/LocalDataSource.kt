package com.example.foodrecipeapp.data

import com.example.foodrecipeapp.database.RecipesDao
import com.example.foodrecipeapp.database.entities.FavoritesEntity
import com.example.foodrecipeapp.database.entities.FoodJokeEntity
import com.example.foodrecipeapp.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        private val recipesDao: RecipesDao
) {

     fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()

    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }

    fun readFavoriteRecipe(): Flow<List<FavoritesEntity>>{
        return recipesDao.readFavoriteRecipes()

    }
    fun readFoodJoke(): Flow<List<FoodJokeEntity>> {
        return recipesDao.readFoodJoke()
    }

    suspend fun insertFavoriteRecipe(recipesEntity: FavoritesEntity){
        recipesDao.insertFavoriteRecipe(recipesEntity)
    }

    suspend fun deleteAllFavoriteRecipes(){
        recipesDao.deleteAllFavoriteRecipes()
    }

    suspend fun deleteFavoriteRecipe(recipesEntity: FavoritesEntity){
        recipesDao.deleteFavoriteRecipe(recipesEntity)
    }

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) {
        recipesDao.insertFoodJoke(foodJokeEntity)
    }
}