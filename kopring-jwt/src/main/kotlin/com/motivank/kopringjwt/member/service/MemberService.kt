package com.motivank.kopringjwt.member.service

import com.motivank.kopringjwt.common.exception.InvalidInputException
import com.motivank.kopringjwt.member.dto.MemberRequest
import com.motivank.kopringjwt.member.entity.Member
import com.motivank.kopringjwt.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class MemberService(
    val memberRepository: MemberRepository
) {

    fun sighUp(memberRequest: MemberRequest): String {
        val member: Member? = memberRepository.findByLoginId(memberRequest.loginId)
        if (member != null) throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")

        memberRepository.save(Member.of(memberRequest))

        return "가입이 완료되었습니다."
    }
}