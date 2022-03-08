package com.mysticraccoon.mobileacademyworkshop.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodCategory(
    val id: String,
    val title: String,
    val url: String
): Parcelable{

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FoodCategory

        if (id != other.id) return false
        if (title != other.title) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }
}