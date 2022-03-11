package com.mysticraccoon.mobileacademyworkshop.ui.foodList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mysticraccoon.mobileacademyworkshop.core.network.Network
import com.mysticraccoon.mobileacademyworkshop.core.room.FoodTrackerDatabase
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodCategoryRepository
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodItemRepository
import com.mysticraccoon.mobileacademyworkshop.databinding.FragmentFoodListBinding
import com.mysticraccoon.mobileacademyworkshop.ui.foodCategories.*

class FoodListFragment : Fragment() {

    private var _binding: FragmentFoodListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FoodListViewModel
    private val arguments: FoodListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryRepository = FoodCategoryRepository(foodCategoryDao = FoodTrackerDatabase.getInstance(requireContext()).foodCategoryDao(),api = Network.retrofit)
        val factory = FoodListViewModelFactory(categoryRepository)

        viewModel = ViewModelProvider(this, factory).get(FoodListViewModel::class.java)
       // viewModel = ViewModelProvider(this).get(FoodListViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupAdapter()
        setupListeners()

        val category = arguments.foodCategory
        viewModel.setCategory(category.title)

    }

    private fun setupListeners() {

        viewModel.showLoading.observe(viewLifecycleOwner){ loading ->
            if(loading){
                binding.categoryProgressBar.visibility = View.VISIBLE
            }else{
                binding.categoryProgressBar.visibility = View.GONE
            }
        }

    }

    private fun setupAdapter() {

        val adapter = FoodListAdapter(FoodItemClicked { item ->
            findNavController().navigate(
                FoodListFragmentDirections.actionFoodListFragmentToFoodDetailsFragment(item)
            )
        })

        binding.foodCategoriesList.adapter = adapter

        viewModel.foodList.observe(viewLifecycleOwner){ list ->
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