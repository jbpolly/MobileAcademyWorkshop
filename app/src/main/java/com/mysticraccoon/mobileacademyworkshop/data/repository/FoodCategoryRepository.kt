package com.mysticraccoon.mobileacademyworkshop.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapter
import com.mysticraccoon.mobileacademyworkshop.core.network.FoodTrackerAPI
import com.mysticraccoon.mobileacademyworkshop.core.room.FoodCategoryDao
import com.mysticraccoon.mobileacademyworkshop.core.utils.Resource
import com.mysticraccoon.mobileacademyworkshop.core.utils.getErrorObjectFromException
import com.mysticraccoon.mobileacademyworkshop.core.utils.getResponseErrorMessage
import com.mysticraccoon.mobileacademyworkshop.data.enums.ErrorType
import com.mysticraccoon.mobileacademyworkshop.data.models.ErrorObject
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces.IFoodCategoryRepository
import com.mysticraccoon.mobileacademyworkshop.data.response.FoodCategoryListResponse
import com.mysticraccoon.mobileacademyworkshop.data.response.FoodItemListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodCategoryRepository(
    private val foodCategoryDao: FoodCategoryDao,
    private val api: FoodTrackerAPI
) :
    IFoodCategoryRepository {

    override val foodCategories: LiveData<List<FoodCategory>> = foodCategoryDao.getFoodCategories()

    override suspend fun getFoodCategoryList(): ErrorObject {

        when(val categoriesListResponse = api.getFoodCategories()){
            is NetworkResponse.Success -> {
                val list = categoriesListResponse.body.list?.map { it.toFoodCategory() }
                withContext(Dispatchers.IO){
                    foodCategoryDao.deleteCategories()
                    foodCategoryDao.saveFoodCategories(list ?: listOf())
                }
                return ErrorObject(isError = false, errorType = null)
            }
            is NetworkResponse.NetworkError -> {
                return ErrorObject(isError = true, errorType = ErrorType.NETWORK)
            }
            is NetworkResponse.ServerError -> {
                return ErrorObject(isError = true, errorType = ErrorType.SERVICE)
            }
            else -> {
                return ErrorObject(isError = true, errorType = ErrorType.UNKNOWN)
            }
        }

//        val result = api.getFoodCategories()
//        return result
    }

    override suspend fun getFoodsFromCategory(category: String): NetworkResponse<FoodItemListResponse, String> {
        return api.getFoodByCategory(category)
    }

    override fun getFoodCategory(): FoodCategory?{
        return foodCategoryDao.getFoodCategories().value?.firstOrNull()
    }



}