package com.mysticraccoon.mobileacademyworkshop.ui.foodList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.mysticraccoon.mobileacademyworkshop.core.utils.SingleLiveEvent
import com.mysticraccoon.mobileacademyworkshop.data.enums.ErrorType
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem
import com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces.IFoodCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodListViewModel(
    private val foodCategoryRepository: IFoodCategoryRepository
) : ViewModel() {

    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val errorType: SingleLiveEvent<ErrorType> = SingleLiveEvent()

    val foodList = MutableLiveData<List<FoodItem>>()

    val isListEmpty = Transformations.map(foodList) { list ->
        if (list.isNullOrEmpty()) {
            return@map true
        }
        false
    }


    fun setCategory(category: String) {
        viewModelScope.launch {
            showLoading.value = true
            when(val listResult = foodCategoryRepository.getFoodsFromCategory(category)){
                is NetworkResponse.Success -> {
                    foodList.value = listResult.body.list?.map { it.toFoodItem() }
                }
                is NetworkResponse.ServerError -> {
                    errorType.value = ErrorType.SERVICE
                }
                is NetworkResponse.NetworkError -> {
                    errorType.value = ErrorType.NETWORK
                }
                else -> {
                    errorType.value = ErrorType.UNKNOWN
                }
            }
            showLoading.value = false
        }

    }

    fun getFoodCategoryAndFoods() {
        viewModelScope.launch {
            showLoading.value = true

            val categoryCall = async { foodCategoryRepository.getFoodCategoryList() }
            val categoryResult = categoryCall.await()

            if (!categoryResult.isError) {
                withContext(Dispatchers.IO) {
                    val category = foodCategoryRepository.getFoodCategory()
                    category?.let { cat ->
                        val foodListResult = foodCategoryRepository.getFoodsFromCategory(cat.title)
                        when (foodListResult) {
                            is NetworkResponse.Success -> {
                                foodList.value = foodListResult.body.list?.map { it.toFoodItem() }
                            }
                            is NetworkResponse.ServerError -> {
                                errorType.value = ErrorType.SERVICE
                            }
                            is NetworkResponse.NetworkError -> {
                                errorType.value = ErrorType.NETWORK
                            }
                            else -> {
                                errorType.value = ErrorType.UNKNOWN
                            }
                        }
                    }
                }
            }
            showLoading.value = false
        }
    }


}