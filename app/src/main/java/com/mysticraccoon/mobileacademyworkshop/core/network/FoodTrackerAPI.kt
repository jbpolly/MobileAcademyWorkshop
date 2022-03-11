package com.mysticraccoon.mobileacademyworkshop.core.network

import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapter
import com.mysticraccoon.mobileacademyworkshop.core.utils.Resource
import com.mysticraccoon.mobileacademyworkshop.data.response.FoodCategoryListResponse
import com.mysticraccoon.mobileacademyworkshop.data.response.FoodItemListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodTrackerAPI {

    @GET("categories.php")
    suspend fun getFoodCategories(): NetworkResponse<FoodCategoryListResponse, String>

    @GET("filter.php")
    suspend fun getFoodByCategory(@Query("c")category: String): NetworkResponse<FoodItemListResponse, String>


}