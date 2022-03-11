package com.mysticraccoon.mobileacademyworkshop.core.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.HttpException
import java.io.IOException


sealed class Resource<T, E>(
    val data: T? = null,
    val message: String? = null,
    val error: E? = null
) {
    class Success<T, E>(data: T) : Resource<T, E>(data)
    class Error<T, E>(message: String, data: T? = null, errorObject: E? = null) :
        Resource<T, E>(data, message, errorObject)
}

fun getResponseErrorMessage(e: Exception): String {
    return when (e) {
        is HttpException -> "Error ${e.code()}: ${e.message()}"
        is IOException -> "Connection error. Your request could not be completed at this time."
        else -> "Unknown error. Your request could not be completed at this time."
    }
}

fun <E : Any> getErrorObjectFromException(e: Exception): E? {
    if (e !is HttpException) return null
    val responseBodyJson = e.response()?.errorBody()?.string().orEmpty()

    if (responseBodyJson.isEmpty()) return null
    return try {
        val gson = Gson()
        val type = object : TypeToken<E>() {}.type
        gson.fromJson(responseBodyJson, type)
    } catch (e: Exception) {
        null
    }
}

fun <T : Any, E : Any> NetworkResponse<T, E>.getBaseResponseErrorString(): String {
    return when (this) {
        is NetworkResponse.Success -> ""
        is NetworkResponse.ServerError -> "Error ${this.code}: ${this.body}"
        is NetworkResponse.NetworkError -> "An error has occurred, your request could not be completed at this time."
        is NetworkResponse.UnknownError -> "An error has occurred, your request could not be completed at this time."
    }
}

