package com.example.foodrecipeapp.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.adapter.RecipesAdapter
import com.example.foodrecipeapp.databinding.FragmentRecipesBinding
import com.example.foodrecipeapp.model.MainViewModel
import com.example.foodrecipeapp.util.NetworkResult
import com.example.foodrecipeapp.util.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private  val args by navArgs<RecipesFragmentArgs>()


    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    var networkStatus = false
    var backOnline = false


    private lateinit var mainViewModel: MainViewModel


    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        setupRecyclerView()
        //requestApiData()
        readDatabase()

        binding.floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_recipesFragment_to_bottomSheetFragment)
               }


        return binding.root
    }

    private fun readDatabase() {
        lifecycleScope.launch {

            mainViewModel.readRecipe.observeOnce(viewLifecycleOwner, { database ->

                if (database.isNotEmpty() && !args.backFromBottomSheet) {
                    Log.d("RecipesFragment", "readDatabase called")
                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }

            })
        }
    }

    private fun requestApiData() {
        Log.d("RecipesFragment","requestApiData called")

        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when(response){
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                            requireContext(),
                            response.toString(),
                            Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading ->{
                    showShimmerEffect()
                }
            }
        })
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipe.observe(viewLifecycleOwner, { database ->

                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                }
            })

        }


    }

    private fun setupRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.recyclerview.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.recyclerview.hideShimmer()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    /*fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }*/


}