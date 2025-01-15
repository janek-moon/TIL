package com.motivank.kopringjwt.member.controller

import com.motivank.kopringjwt.common.dto.BaseResponse
import com.motivank.kopringjwt.member.dto.MemberRequest
import com.motivank.kopringjwt.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController(
    val memberService: MemberService
) {

    @PostMapping("signup")
    fun signUp(@RequestBody @Valid memberRequest: MemberRequest): BaseResponse<Unit> {
        val resultMessage = memberService.sighUp(memberRequest = memberRequest)
        return BaseResponse(message = resultMessage)
    }

}