package com.example.foodrecipeapp.recipesAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foodrecipeapp.R

class RowBindingAdapter {

    companion object{

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView,url:String){
            imageView.load(url){
                crossfade(600)
                //error(R.drawable.ic_error_placeholder)
            }

        }

        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setEmployeeAge(textView: TextView, likes:Int ){
           textView.text = likes.toString()

        }


        @BindingAdapter("setNumberOfMinutes")
        @JvmStatic
        fun setEmployeesalary(textView: TextView, salary:Int ){
            textView.text = salary.toString()

        }
        @BindingAdapter("applyVegan")
        @JvmStatic
        fun applyVegan(view: View, vegan:Boolean){

            if(vegan){
                when(view){
                    is TextView->{
                        view.setTextColor(
                                ContextCompat.getColor(
                                view.context,
                                R.color.green)
                        )
                    }
                    is ImageView->{
                        view.setColorFilter(
                                ContextCompat.getColor(
                                        view.context,
                                        R.color.green)
                        )
                    }
                }
            }

        }
    }

}