package com.example.foodrecipeapp.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.adapter.PagerAdapter
import com.example.foodrecipeapp.database.entities.FavoritesEntity
import com.example.foodrecipeapp.model.MainViewModel
import com.example.foodrecipeapp.viewpagerfragmnets.IngredientsFragment
import com.example.foodrecipeapp.viewpagerfragmnets.InstructionsFragment
import com.example.foodrecipeapp.viewpagerfragmnets.OverviewFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.recipes_bottom_sheet.*
import java.lang.Exception

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val args by navArgs<DetailActivityArgs>()

    val mainViewModel:MainViewModel by viewModels()

    var recipeSaved = false
    private var recipeSavedId =0


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        toolbar.setTitleTextColor(ContextCompat.getColor(this,Color.WHITE))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)

        val adapter = PagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager
        )

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        val menuItem = menu?.findItem(R.id.save_to_favorites_menu)
        checkSaveRecipes(menuItem)

        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)    {
            finish()
        }
        else if(item.itemId == R.id.save_to_favorites_menu && !recipeSaved)    {
          saveToFavorites(item)
        }
        else if(item.itemId == R.id.save_to_favorites_menu && recipeSaved)    {
            removeFromFavorite(item)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun checkSaveRecipes(menuItem: MenuItem?) {
        mainViewModel.readFavoriteRecipe.observe(this,{favoriteEntity->
            try {
                for (saveRecipe in favoriteEntity){
                    if (saveRecipe.result.recipeId == args.result.recipeId){
                        changeMenuItemColor(menuItem!!,R.color.yellow)
                        recipeSavedId = saveRecipe.id
                        recipeSaved = true
                    }else{
                        changeMenuItemColor(menuItem!!,R.color.white)

                    }
                }

            }catch (e:Exception){
                Log.d("DetailActivit",e.message.toString())

            }

        })
    }

    private fun saveToFavorites(item: MenuItem) {
        val favoritesEntity= FavoritesEntity(0,args.result)
        mainViewModel.insertFavoriteRecipe(favoritesEntity)
        changeMenuItemColor(item,R.color.yellow)
        showSnackBar("Recipe saved.")
        recipeSaved = true
    }

    private  fun removeFromFavorite(item:MenuItem){

        val favoritesEntity= FavoritesEntity(0,args.result)
        mainViewModel.deleteFavoriteRecipe(favoritesEntity)
        changeMenuItemColor(item,R.color.white)
        showSnackBar("Remove from favorites.")
        recipeSaved = false

    }
    private fun showSnackBar(message: String) {

        Snackbar.make(detailsLayout,message,Snackbar.LENGTH_SHORT).setAction("Okay"){}.show()

    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {

        item.icon.setTint(ContextCompat.getColor(this,color))


    }
}