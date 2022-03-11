package com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces

import androidx.lifecycle.LiveData
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem

interface IFoodItemRepository {

    val savedItems: LiveData<List<FoodItem>>
    suspend fun getFoodItemById(id: String): FoodItem?
    suspend fun deleteFoodItemById(id: String)
    suspend fun saveFoodItem(foodItem: FoodItem)
    suspend fun deleteAllSavedFoodItems()

}