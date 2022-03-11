package com.mysticraccoon.mobileacademyworkshop.ui.foodDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mysticraccoon.mobileacademyworkshop.R
import com.mysticraccoon.mobileacademyworkshop.core.network.Network
import com.mysticraccoon.mobileacademyworkshop.core.room.FoodTrackerDatabase
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodCategoryRepository
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodItemRepository
import com.mysticraccoon.mobileacademyworkshop.databinding.FragmentFoodDetailsBinding
import com.mysticraccoon.mobileacademyworkshop.ui.foodCategories.FoodCategoriesViewModel
import com.mysticraccoon.mobileacademyworkshop.ui.foodCategories.FoodCategoriesViewModelFactory

class FoodDetailsFragment: Fragment() {

    private var _binding: FragmentFoodDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FoodDetailsViewModel
    val arguments: FoodDetailsFragmentArgs by navArgs()

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

        val repository = FoodItemRepository( FoodTrackerDatabase.getInstance(requireContext()).foodItemDao())
        val factory = FoodDetailsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(FoodDetailsViewModel::class.java)
//        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val item = arguments.foodItem

        setupItem(item)
        setupObservers()
        viewModel.checkIsFavorite(item)

    }

    private fun setupItem(item: FoodItem) {
        binding.detailsDishName.text = item.name
        binding.detailsDishCategory.text = item.category
        binding.detailsLocationText.text = item.area
        Glide.with(requireContext())
            .load(item.url)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_no_food)
            .into(binding.detailsThumb)

        binding.fabFavorite.setOnClickListener {
            if (viewModel.isFavorite.value == true) {
                viewModel.removeFavorite(item)
            } else {
                viewModel.addToFavorite(item)
            }
        }
    }

    private fun setupObservers() {
        viewModel.finishFlow.observe(viewLifecycleOwner) {
            findNavController().navigate(
                FoodDetailsFragmentDirections.actionFoodDetailsFragmentToSavedFoodFragment()
            )
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { favorite ->
            if(favorite){
                binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_24))
            }else{
                binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border_24))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}