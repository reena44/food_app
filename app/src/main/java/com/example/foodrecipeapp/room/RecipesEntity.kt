package com.example.foodrecipeapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipeapp.model.FoodRecipe
import com.example.foodrecipeapp.util.Constants.Companion.RECIPES_TABLE


@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
var foodRecipe: FoodRecipe

) {

    @PrimaryKey (autoGenerate = false)
    var id:Int = 0
}