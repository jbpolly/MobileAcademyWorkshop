package com.mysticraccoon.mobileacademyworkshop.ui.foodDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mysticraccoon.mobileacademyworkshop.databinding.FragmentFoodDetailsBinding
import com.mysticraccoon.mobileacademyworkshop.ui.foodCategories.FoodCategoriesViewModel

class FoodDetailsFragment: Fragment() {

    private var _binding: FragmentFoodDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FoodDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FoodDetailsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.fabFavorite.setOnClickListener {
            findNavController().navigate(
                FoodDetailsFragmentDirections.actionFoodDetailsFragmentToSavedFoodFragment()
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}