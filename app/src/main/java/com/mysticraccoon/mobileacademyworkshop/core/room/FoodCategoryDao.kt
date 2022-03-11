package com.mysticraccoon.mobileacademyworkshop.core.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mysticraccoon.mobileacademyworkshop.core.utils.FoodTrackerConstants
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory

@Dao
interface FoodCategoryDao {

    @Query("SELECT * FROM ${FoodTrackerConstants.foodCategoryTableName}")
    fun getFoodCategories(): LiveData<List<FoodCategory>>

    @Query("DELETE from ${FoodTrackerConstants.foodCategoryTableName}")
    suspend fun deleteCategories()

    @Insert
    suspend fun saveFoodCategories(list: List<FoodCategory>)

}