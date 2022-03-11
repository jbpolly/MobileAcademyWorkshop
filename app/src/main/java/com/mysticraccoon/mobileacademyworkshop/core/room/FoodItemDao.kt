package com.mysticraccoon.mobileacademyworkshop.core.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mysticraccoon.mobileacademyworkshop.core.utils.FoodTrackerConstants
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem

@Dao
interface FoodItemDao{

    @Query("SELECT * FROM ${FoodTrackerConstants.foodItemTableName}")
    fun getFoodItems(): LiveData<List<FoodItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFoodItem(item: FoodItem)

    @Query("SELECT * FROM ${FoodTrackerConstants.foodItemTableName} where ${FoodTrackerConstants.foodItemIdColumnName} = :itemId")
    suspend fun getSavedFoodById(itemId: String): FoodItem?

    @Query("DELETE FROM ${FoodTrackerConstants.foodItemTableName} WHERE ${FoodTrackerConstants.foodItemIdColumnName} = :id")
    suspend fun deleteFoodItemById(id: String)

    @Query("DELETE from ${FoodTrackerConstants.foodItemTableName}")
    suspend fun deleteAllFoodItems()


}