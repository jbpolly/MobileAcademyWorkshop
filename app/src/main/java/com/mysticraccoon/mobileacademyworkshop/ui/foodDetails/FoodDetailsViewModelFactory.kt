package com.mysticraccoon.mobileacademyworkshop.ui.foodDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodItemRepository

class FoodDetailsViewModelFactory(private val foodItemRepository: FoodItemRepository): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FoodDetailsViewModel::class.java)){
            return FoodDetailsViewModel(foodItemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}