package com.motivank.kopringjwt.common.status

enum class Gender(val description: String) {
    MAN("남"),
    WOMAN("여")
}

enum class ResultCode(val message: String) {
    SUCCESS("성공"),
    ERROR("실패")
}

enum class ROLE {
    MEMBER,
}