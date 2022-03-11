package com.mysticraccoon.mobileacademyworkshop.ui.foodCategories

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysticraccoon.mobileacademyworkshop.core.utils.SingleLiveEvent
import com.mysticraccoon.mobileacademyworkshop.data.enums.ErrorType
import com.mysticraccoon.mobileacademyworkshop.data.repository.interfaces.IFoodCategoryRepository
import kotlinx.coroutines.launch

class FoodCategoriesViewModel(private val categoryRepository: IFoodCategoryRepository): ViewModel() {

    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val errorType: SingleLiveEvent<ErrorType> = SingleLiveEvent()

    val foodCategories = categoryRepository.foodCategories
    val isListEmpty = Transformations.map(foodCategories){ list ->
        if(list.isNullOrEmpty()){
            return@map true
        }
        false
    }

    init {
        refreshFoodCategories()
    }

    fun refreshFoodCategories(){
        viewModelScope.launch {
            showLoading.value = true
            val result = categoryRepository.getFoodCategoryList()
            if(result.isError){
                errorType.value = result.errorType ?: ErrorType.UNKNOWN
            }
            showLoading.value = false
        }
    }



    

}