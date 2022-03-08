package com.mysticraccoon.mobileacademyworkshop.ui.savedFoods

import androidx.lifecycle.ViewModel
import com.mysticraccoon.mobileacademyworkshop.data.models.FoodItem

class SavedFoodViewModel : ViewModel() {


    val fakeSavedList = listOf<FoodItem>(
        FoodItem(id = "1", name = "Batata", url = "", category = "good food", area = ""),
        FoodItem(id = "2", name = "Miojo", url = "", category = "good food", area = ""),
        FoodItem(id = "3", name = "Alface", url = "", category = "sad food", area = ""),
        FoodItem(id = "4", name = "Pizza", url = "", category = "good food", area = ""),
        FoodItem(id = "5", name = "A La Minuta", url = "", category = "good food", area = ""),
    )




}