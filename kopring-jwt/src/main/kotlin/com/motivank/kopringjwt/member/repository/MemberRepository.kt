package com.motivank.kopringjwt.member.repository

import com.motivank.kopringjwt.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByLoginId(loginId: String): Member?

}