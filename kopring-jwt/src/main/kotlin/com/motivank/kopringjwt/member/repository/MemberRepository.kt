package com.motivank.kopringjwt.member.repository

import com.motivank.kopringjwt.member.entity.Member
import com.motivank.kopringjwt.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByLoginId(loginId: String): Member?

}

interface MemberRoleRepository : JpaRepository<MemberRole, Long> {

    fun findByMemberId(memberId: Long): List<MemberRole>

}