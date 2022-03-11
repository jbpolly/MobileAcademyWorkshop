package com.mysticraccoon.mobileacademyworkshop.ui.savedFoods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mysticraccoon.mobileacademyworkshop.R
import com.mysticraccoon.mobileacademyworkshop.core.room.FoodTrackerDatabase
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodItemRepository
import com.mysticraccoon.mobileacademyworkshop.databinding.FragmentSavedFoodBinding
import com.mysticraccoon.mobileacademyworkshop.ui.foodDetails.FoodDetailsViewModelFactory
import com.mysticraccoon.mobileacademyworkshop.ui.foodList.FoodListFragmentDirections

class SavedFoodFragment: Fragment() {

    private var _binding: FragmentSavedFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SavedFoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedFoodBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = FoodItemRepository( FoodTrackerDatabase.getInstance(requireContext()).foodItemDao())
        val factory = SavedFoodViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(SavedFoodViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupAdapter()

        binding.fabBrowse.setOnClickListener {
            findNavController().navigate(R.id.action_savedFoodFragment_to_foodCategoriesFragment)
        }

    }

    private fun setupAdapter() {
        val adapter = SavedFoodDiffAdapter(SavedFoodItemClicked(block = { item ->
            //go to food details
            findNavController().navigate(SavedFoodFragmentDirections.actionSavedFoodFragmentToFoodDetailsFragment(item))

        }, deleteClick = { item ->
            //remove item from dao and list
            viewModel.deleteFoodItem(item)
        }))
        binding.savedFoodList.adapter = adapter
        viewModel.savedFoods.observe(viewLifecycleOwner){ list ->
            adapter.submitList(list)
        }

    }

    override fun onDestroyView() {
        binding.savedFoodList.adapter = null
        super.onDestroyView()
        _binding = null
    }


}