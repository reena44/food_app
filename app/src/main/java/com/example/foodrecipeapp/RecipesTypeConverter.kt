package com.example.foodrecipeapp

import androidx.room.TypeConverter
import com.example.foodrecipeapp.model.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    var gson = Gson()


    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe):String{
       return gson.toJson(foodRecipe)

    }

    @TypeConverter
    fun stringTofoodRecipes(data: String):FoodRecipe{
        val listType = object :TypeToken<FoodRecipe>(){}.type
        return gson.fromJson(data,listType)

    }
}