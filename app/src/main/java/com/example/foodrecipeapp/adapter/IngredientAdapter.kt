package com.example.foodrecipeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.model.ExtendedIngredient
import com.example.foodrecipeapp.util.Constants.Companion.BASE_IMAGE_URL
import com.example.foody.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.ingredient_row_layout.view.*
import java.util.zip.Inflater

class IngredientAdapter:RecyclerView.Adapter<IngredientAdapter.MyViewHolder>() {


    var ingredientList  = emptyList<ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  MyViewHolder{

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ingredient_row_layout,parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.ingredient_imageView.load(BASE_IMAGE_URL + ingredientList[position].image){
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
        holder.itemView.ingredient_name.text = ingredientList[position].name
        holder.itemView.ingredient_amount.text = ingredientList[position].amount.toString()
        holder.itemView.ingredient_unit.text = ingredientList[position].unit
        holder.itemView.ingredient_consistency.text = ingredientList[position].consistency
        holder.itemView.ingredient_original.text = ingredientList[position].original
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientsDiffUtil =
                RecipesDiffUtil(ingredientList, newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientList = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }

}