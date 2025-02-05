package com.motivank.kopringjwt.member.service

import com.motivank.kopringjwt.common.authority.JwtTokenProvider
import com.motivank.kopringjwt.common.authority.TokenInfo
import com.motivank.kopringjwt.common.exception.InvalidInputException
import com.motivank.kopringjwt.member.dto.LoginRequest
import com.motivank.kopringjwt.member.dto.MemberCreateRequest
import com.motivank.kopringjwt.member.dto.MemberResponse
import com.motivank.kopringjwt.member.dto.MemberUpdateRequest
import com.motivank.kopringjwt.member.entity.Member
import com.motivank.kopringjwt.member.entity.MemberRole
import com.motivank.kopringjwt.member.repository.MemberRepository
import com.motivank.kopringjwt.member.repository.MemberRoleRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    val memberRepository: MemberRepository,
    val memberRoleRepository: MemberRoleRepository,
    val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun sighUp(memberRequest: MemberCreateRequest): String {
        val member: Member? = memberRepository.findByLoginId(memberRequest.loginId)
        if (member != null) throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")

        val encodedPassword = passwordEncoder.encode(memberRequest.password)
        val newMember = Member.of(memberRequest, encodedPassword)
        memberRepository.save(newMember)

        val memberRole = MemberRole.of(newMember)
        memberRoleRepository.save(memberRole)

        return "회원가입이 완료되었습니다."
    }

    fun login(loginRequest: LoginRequest): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginRequest.loginId, loginRequest.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }

    fun searchMyInfo(id: Long): MemberResponse {
        val member: Member = memberRepository.findById(id).orElseThrow { InvalidInputException("id", "회원번호: ${id}는 존재하지 않는 회원입니다.") }

        return member.toDto()
    }

    @Transactional
    fun updateMyInfo(memberRequest: MemberUpdateRequest): String {
        val member: Member = memberRepository.findById(memberRequest.id!!).orElseThrow { InvalidInputException("id", "회원번호: ${memberRequest.id}는 존재하지 않는 회원입니다.") }

        member.update(memberRequest)

        return "회원정보가 수정되었습니다."
    }

}