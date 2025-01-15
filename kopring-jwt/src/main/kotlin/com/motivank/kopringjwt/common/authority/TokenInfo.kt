package com.motivank.kopringjwt.common.authority

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
)