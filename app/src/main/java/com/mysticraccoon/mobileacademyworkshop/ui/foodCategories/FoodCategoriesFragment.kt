package com.mysticraccoon.mobileacademyworkshop.ui.foodCategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mysticraccoon.mobileacademyworkshop.core.network.Network
import com.mysticraccoon.mobileacademyworkshop.core.room.FoodTrackerDatabase
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodCategoryRepository
import com.mysticraccoon.mobileacademyworkshop.databinding.FragmentFoodCategoriesBinding

class FoodCategoriesFragment : Fragment() {

    private var _binding: FragmentFoodCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FoodCategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = FoodCategoryRepository(foodCategoryDao = FoodTrackerDatabase.getInstance(requireContext()).foodCategoryDao(),api = Network.retrofit)
        val factory = FoodCategoriesViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(FoodCategoriesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        binding.exampleCategory.setOnClickListener {
//            findNavController().navigate(
//                FoodCategoriesFragmentDirections.actionFoodCategoriesFragmentToFoodListFragment(
//                    foodCategory = FoodCategory("teste", "titulo 1", "url")
//                )
//            )
//        }
        setupObservers()
        setupAdapter()
    }

    private fun setupObservers() {

        viewModel.showLoading.observe(viewLifecycleOwner){ loading ->
            if(loading){
                binding.categoryProgressBar.visibility = View.VISIBLE
            }else{
                binding.categoryProgressBar.visibility = View.GONE
            }
        }

    }

    private fun setupAdapter() {

        val manager = GridLayoutManager(requireActivity(), 2)
        binding.foodCategoriesList.layoutManager = manager

        val adapter = FoodCategoryAdapter(FoodCategoryClicked { category ->
            findNavController().navigate(
                FoodCategoriesFragmentDirections.actionFoodCategoriesFragmentToFoodListFragment(
                    foodCategory = category
                )
            )
        })
        binding.foodCategoriesList.adapter = adapter

        viewModel.foodCategories.observe(viewLifecycleOwner){ list ->
            list?.let {
                adapter.submitList(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}