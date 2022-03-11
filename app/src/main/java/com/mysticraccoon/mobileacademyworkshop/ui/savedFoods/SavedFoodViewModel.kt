package com.mysticraccoon.mobileacademyworkshop.ui.savedFoods

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedFoodViewModel(private val foodItemRepository: FoodItemRepository) : ViewModel() {


    val savedFoods = foodItemRepository.savedItems
    val isListEmpty = Transformations.map(savedFoods){ list ->
        if(list.isNullOrEmpty()){
            return@map true
        }
        false
    }

    fun deleteFoodItem(item: FoodItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                foodItemRepository.deleteFoodItemById(item.id)
            }
        }

    }

//    val fakeSavedList = listOf<FoodItem>(
//        FoodItem(id = "1", name = "Batata", url = "", category = "good food", area = ""),
//        FoodItem(id = "2", name = "Miojo", url = "", category = "good food", area = ""),
//        FoodItem(id = "3", name = "Alface", url = "", category = "sad food", area = ""),
//        FoodItem(id = "4", name = "Pizza", url = "", category = "good food", area = ""),
//        FoodItem(id = "5", name = "A La Minuta", url = "", category = "good food", area = ""),
//    )




}