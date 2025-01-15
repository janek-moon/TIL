package com.motivank.kopringjwt.member.entity

import com.motivank.kopringjwt.common.status.Gender
import com.motivank.kopringjwt.member.dto.MemberRequest
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])])
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    val loginId: String,

    @Column(nullable = false, length = 100)
    val password: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val birthDate: LocalDate,

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    val gender: Gender,

    @Column(nullable = false, length = 30)
    val email: String,
) {
    companion object {
        fun of(memberRequest: MemberRequest): Member {
            return Member(
                id = null,
                loginId = memberRequest.loginId,
                password = memberRequest.password,
                name = memberRequest.name,
                birthDate = memberRequest.birthDate,
                gender = memberRequest.gender,
                email = memberRequest.email,
            )
        }
    }
}