package com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces

import androidx.lifecycle.LiveData
import com.haroldadmin.cnradapter.NetworkResponse
import com.mysticraccoon.mobileacademyworkshop.core.utils.Resource
import com.mysticraccoon.mobileacademyworkshop.data.models.ErrorObject
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.response.FoodCategoryListResponse
import com.mysticraccoon.mobileacademyworkshop.data.response.FoodItemListResponse

interface IFoodCategoryRepository {

    val foodCategories: LiveData<List<FoodCategory>>
    suspend fun getFoodCategoryList(): ErrorObject
    suspend fun getFoodsFromCategory(category: String): NetworkResponse<FoodItemListResponse, String>

    fun getFoodCategory(): FoodCategory?
}