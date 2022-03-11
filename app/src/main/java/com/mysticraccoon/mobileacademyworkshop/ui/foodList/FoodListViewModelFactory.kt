package com.mysticraccoon.mobileacademyworkshop.ui.foodList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces.IFoodCategoryRepository

class FoodListViewModelFactory(
    private val categoryRepository: IFoodCategoryRepository,
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodListViewModel::class.java)) {
            return FoodListViewModel(categoryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
