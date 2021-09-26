package com.example.foodrecipeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipeapp.database.entities.FavoritesEntity
import com.example.foodrecipeapp.database.entities.RecipesEntity
import com.example.foodrecipeapp.util.RecipesTypeConverter


@Database(
        entities = [RecipesEntity::class,FavoritesEntity::class],
        version = 2,
        exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase :RoomDatabase(){


    abstract fun recipesDao():RecipesDao
}