package com.example.foodrecipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp.databinding.RecipesRowLayoutBinding
import com.example.foodrecipeapp.model.FoodRecipe
import com.example.foodrecipeapp.model.Result
import com.example.foody.util.RecipesDiffUtil

class RecipesAdapter:RecyclerView.Adapter <RecipesAdapter.ViewHolder>(){

    private var recipes = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cureentRecipes: com.example.foodrecipeapp.model.Result = recipes[position]
        holder.bind(cureentRecipes)


    }


    fun setData(newData: FoodRecipe)
    {
        val recipesDiffUtil = RecipesDiffUtil(recipes,newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results

        diffUtilResult.dispatchUpdatesTo(this)

    }

    override fun getItemCount(): Int {
        return recipes.size
    }
    class ViewHolder (var binding:RecipesRowLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(result: com.example.foodrecipeapp.model.Result){
            binding.result = result
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }

    }

}