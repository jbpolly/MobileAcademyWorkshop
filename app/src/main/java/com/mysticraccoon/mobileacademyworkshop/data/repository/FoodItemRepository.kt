package com.mysticraccoon.mobileacademyworkshop.data.repository

import androidx.lifecycle.LiveData
import com.mysticraccoon.mobileacademyworkshop.core.room.FoodItemDao
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces.IFoodItemRepository

class FoodItemRepository(private val foodItemDao: FoodItemDao): IFoodItemRepository {

    override val savedItems: LiveData<List<FoodItem>> = foodItemDao.getFoodItems()

    override suspend fun getFoodItemById(id: String): FoodItem? {
        if(id.isEmpty()) return null
        return foodItemDao.getSavedFoodById(id)
    }

    override suspend fun deleteFoodItemById(id: String) {
        if(id.isEmpty()) return
        return foodItemDao.deleteFoodItemById(id)
    }

    override suspend fun saveFoodItem(foodItem: FoodItem) {
        foodItemDao.saveFoodItem(foodItem)
    }

    override suspend fun deleteAllSavedFoodItems() {
        foodItemDao.deleteAllFoodItems()
    }

}