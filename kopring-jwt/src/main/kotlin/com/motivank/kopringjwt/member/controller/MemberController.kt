package com.motivank.kopringjwt.member.controller

import com.motivank.kopringjwt.common.authority.TokenInfo
import com.motivank.kopringjwt.common.dto.BaseResponse
import com.motivank.kopringjwt.common.dto.CustomUser
import com.motivank.kopringjwt.member.dto.LoginRequest
import com.motivank.kopringjwt.member.dto.MemberCreateRequest
import com.motivank.kopringjwt.member.dto.MemberResponse
import com.motivank.kopringjwt.member.dto.MemberUpdateRequest
import com.motivank.kopringjwt.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/member")
class MemberController(
    val memberService: MemberService
) {

    @PostMapping("signup")
    fun signUp(@RequestBody @Valid memberRequest: MemberCreateRequest): BaseResponse<Unit> {
        val resultMessage = memberService.sighUp(memberRequest = memberRequest)
        return BaseResponse(message = resultMessage)
    }

    @PostMapping("login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginRequest = loginRequest)
        return BaseResponse(data = tokenInfo)
    }

    @GetMapping
    fun searchMyInfo(): BaseResponse<MemberResponse> {
        val userId = getUserId()
        val memberResponse = memberService.searchMyInfo(userId)

        return BaseResponse(data = memberResponse)
    }

    @PatchMapping
    fun updateMyInfo(@RequestBody @Valid memberRequest: MemberUpdateRequest): BaseResponse<Unit> {
        memberRequest.id = getUserId()
        val resultMessage = memberService.updateMyInfo(memberRequest = memberRequest)
        return BaseResponse(message = resultMessage)
    }

    private fun getUserId(): Long {
        return (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
    }

}