package com.mysticraccoon.mobileacademyworkshop.core.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.io.IOException


sealed class Resource<T, E>(val data: T? = null, val message: String? = null, val error: E? = null) {
    class Success<T, E>(data: T) : Resource<T, E>(data)
    class Error<T, E>(message: String, data: T? = null, errorObject: E? = null) : Resource<T, E>(data, message, errorObject)
    class Loading<T, E>(data: T? = null) : Resource<T, E>(data)
}

fun getResponseErrorMessage(e: Exception): String {
    return when (e) {
        is HttpException -> "Error ${e.code()}: ${e.message()}"
        is IOException -> "Connection error. Your request could not be completed at this time."
        else -> "Unknown error. Your request could not be completed at this time."
    }
}

fun <E : Any> getErrorObjectFromException(e: Exception): E? {
    if(e !is HttpException) return null
    val responseBodyJson =  e.response()?.errorBody()?.string().orEmpty()

    if(responseBodyJson.isEmpty()) return null
    return try {
        val gson = Gson()
        val type = object : TypeToken<E>() {}.type
        gson.fromJson(responseBodyJson, type)
    }catch (e:Exception){
        null
    }
}

//fun <E : Any> getErrorObjectFromExceptionRetrofit(e: Exception): E? {
//    if(e !is HttpException) return null
//    val responseBodyJson =  e.response()?.errorBody()?.string().orEmpty()
//
//    if(responseBodyJson.isEmpty()) return null
//    return try {
//        val gson = Gson()
//        val type = object : TypeToken<E>() {}.type
//        gson.fromJson(responseBodyJson, type)
//    }catch (e:Exception){
//        null
//    }
//}

