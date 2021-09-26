package com.example.foodrecipeapp.favorite

import android.os.Bundle
import android.os.Message
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.adapter.FavoriteRecipesAdapter
import com.example.foodrecipeapp.databinding.FragmentFavoritrReciepesBinding
import com.example.foodrecipeapp.model.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.recipes_bottom_sheet.*
import java.util.zip.Inflater


@AndroidEntryPoint
class FavoriteReciepesFragment : Fragment() {

    private val adpter :FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter(requireActivity(),mainViewModel) }
    private val mainViewModel:MainViewModel by viewModels()

    private var _binding : FragmentFavoritrReciepesBinding?= null
    private val binding get()= _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentFavoritrReciepesBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = adpter

        setHasOptionsMenu(true)


        setRecyclerView(binding.favoriteRecipesRecyclerView)
        mainViewModel.readFavoriteRecipe.observe(viewLifecycleOwner,{ favoriteEntity->
            adpter.setData(favoriteEntity)

        })
        return view
    }



    private fun setRecyclerView(recyclerView: RecyclerView){
        recyclerView.adapter = adpter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll_favorite_recipe_menu){
            mainViewModel.deleteAllFavoriteRecipe()
            showSnackBar()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipe_menu,menu)
    }

    private  fun showSnackBar(){
        Snackbar.make(binding.root,"All recipes removed.",
                Snackbar.LENGTH_SHORT)
                .setAction("Okay"){}.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adpter.clearContextualActionMode()
    }



}