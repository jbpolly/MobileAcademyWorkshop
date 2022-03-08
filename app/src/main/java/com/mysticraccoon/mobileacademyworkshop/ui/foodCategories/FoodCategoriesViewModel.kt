package com.mysticraccoon.mobileacademyworkshop.ui.foodCategories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory

class FoodCategoriesViewModel: ViewModel() {

    val foodCategoryList = MutableLiveData<List<FoodCategory>>()


    

}