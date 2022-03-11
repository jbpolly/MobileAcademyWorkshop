package com.mysticraccoon.mobileacademyworkshop.ui.savedFoods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodItemRepository

class SavedFoodViewModelFactory(private val foodItemRepository: FoodItemRepository): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SavedFoodViewModel::class.java)){
            return SavedFoodViewModel(foodItemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}