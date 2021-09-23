package com.example.foodrecipeapp.network
import com.example.foodrecipeapp.model.FoodRecipe
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
        private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>): retrofit2.Response<FoodRecipe> {

        return foodRecipesApi.getRecipes(queries)
    }

}