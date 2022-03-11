package com.mysticraccoon.mobileacademyworkshop.ui.foodCategories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mysticraccoon.mobileacademyworkshop.core.room.FoodCategoryDao
import com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces.IFoodCategoryRepository

class FoodCategoriesViewModelFactory(private val repository: IFoodCategoryRepository): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FoodCategoriesViewModel::class.java)){
            return FoodCategoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}