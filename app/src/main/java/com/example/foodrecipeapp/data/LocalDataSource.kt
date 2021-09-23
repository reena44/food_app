package com.example.foodrecipeapp.data

import com.example.foodrecipeapp.model.FoodRecipe
import com.example.foodrecipeapp.room.RecipesDao
import com.example.foodrecipeapp.room.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        private val recipesDao: RecipesDao
) {

     fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()

    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }
}