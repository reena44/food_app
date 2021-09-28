package com.example.foodrecipeapp.joke

import android.net.Network
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foodrecipeapp.database.entities.FoodJokeEntity
import com.example.foodrecipeapp.model.FoodJoke
import com.example.foodrecipeapp.util.NetworkResult
import com.google.android.material.card.MaterialCardView


class FoodJokeBinding {


    companion object {
        @BindingAdapter("readApiResponse3", "readDatabase3", requireAll = false)
        @JvmStatic
        fun setCardAndProgressVisibility(view: View,
                                         apiRespose: NetworkResult<FoodJoke>?,
                                         database: List<FoodJokeEntity>?) {

            when (apiRespose) {

                is NetworkResult.Loading -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.VISIBLE

                        }
                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                        }
                    }

                }
                is NetworkResult.Error -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE

                        }
                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                            if (database != null) {
                                if (database.isEmpty()) {
                                    view.visibility = View.INVISIBLE
                                }
                            }
                        }
                    }
                }
                is NetworkResult.Success -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE

                        }
                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                            if (database != null) {
                                if (database.isEmpty()) {
                                    view.visibility = View.INVISIBLE
                                }
                            }
                        }
                    }
                }
            }
        }


        @BindingAdapter("readApiResponse4", "readDatabase4", requireAll = false)
        @JvmStatic
        fun setErrorViewVisibility(view: View,
                                   apiRespose: NetworkResult<FoodJoke>?,
                                   database: List<FoodJokeEntity>?) {

            if (database != null) {
                view.visibility = View.VISIBLE
                if (view is TextView) {
                    if (apiRespose != null) {
                        view.text = apiRespose.mesaage.toString()
                    }
                }
            }
            if (apiRespose is NetworkResult.Success) {
                view.visibility = View.INVISIBLE

            }
        }

    }
}