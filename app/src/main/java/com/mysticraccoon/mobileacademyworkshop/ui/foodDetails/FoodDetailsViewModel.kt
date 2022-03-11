package com.mysticraccoon.mobileacademyworkshop.ui.foodDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysticraccoon.mobileacademyworkshop.core.utils.SingleLiveEvent
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.repository.FoodItemRepository
import com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces.IFoodItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodDetailsViewModel(private val foodItemRepository: IFoodItemRepository): ViewModel() {


    val isFavorite = SingleLiveEvent<Boolean>()
    val finishFlow = SingleLiveEvent<Boolean>()

    fun checkIsFavorite(item: FoodItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val item = foodItemRepository.getFoodItemById(item.id)
                //isFavorite.value = item != null
                isFavorite.postValue(item != null)
            }
        }
    }

    fun addToFavorite(item: FoodItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val item = foodItemRepository.saveFoodItem(item)
               // finishFlow.value = true
                finishFlow.postValue(true)
            }
        }
    }

    fun removeFavorite(item: FoodItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                foodItemRepository.deleteFoodItemById(item.id)
               // finishFlow.value = true
                finishFlow.postValue(true)
            }
        }
    }

}