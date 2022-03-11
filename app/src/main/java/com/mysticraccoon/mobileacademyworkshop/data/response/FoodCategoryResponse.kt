package com.mysticraccoon.mobileacademyworkshop.data.response

import com.mysticraccoon.mobileacademyworkshop.data.models.FoodCategory
import com.squareup.moshi.Json


data class FoodCategoryListResponse(
    @Json(name = "categories")
    val list: List<FoodCategoryResponse>?
)

data class FoodCategoryResponse(
    @Json(name = "idCategory")
    val id: String = "",
    @Json(name = "strCategory")
    val title: String? = "",
    @Json(name = "strCategoryThumb")
    val url: String? = ""
){

    fun toFoodCategory(): FoodCategory {
        return FoodCategory(
            id = id,
            title = title.orEmpty(),
            url = url.orEmpty()
        )
    }

}