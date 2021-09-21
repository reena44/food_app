package com.example.foodrecipeapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipeapp.RecipesTypeConverter


@Database(
        entities = [RecipesEntity::class],
        version = 1,
        exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase :RoomDatabase(){


    abstract fun recipesDao():RecipesDao
}