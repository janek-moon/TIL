package com.motivank.kopringjwt.common.dto

import com.motivank.kopringjwt.common.status.ResultCode

data class BaseResponse<T>(
    val resultCode: String = ResultCode.SUCCESS.name,
    val data: T? = null,
    val message: String? = ResultCode.SUCCESS.message
)