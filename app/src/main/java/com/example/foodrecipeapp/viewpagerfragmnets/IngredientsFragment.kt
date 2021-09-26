package com.example.foodrecipeapp.viewpagerfragmnets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.adapter.IngredientAdapter
import com.example.foodrecipeapp.model.Result
import kotlinx.android.synthetic.main.fragment_ingredients.view.*


class IngredientsFragment : Fragment() {

    private val mAdapetr:IngredientAdapter by lazy { IngredientAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_ingredients, container, false)
        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipeBundle")
        setRecyclerView(view)
        myBundle?.extendedIngredients.let{mAdapetr.setData(it!! )

        }

        return view
    }

   fun  setRecyclerView(view:View){

       view.recycler_ingredient.adapter = mAdapetr
       view.recycler_ingredient.layoutManager = LinearLayoutManager(requireContext())



    }
}