package com.mysticraccoon.mobileacademyworkshop.data.models

import com.mysticraccoon.mobileacademyworkshop.data.enums.ErrorType

data class ErrorObject(
    val isError: Boolean = false,
    val errorType: ErrorType? = null
)