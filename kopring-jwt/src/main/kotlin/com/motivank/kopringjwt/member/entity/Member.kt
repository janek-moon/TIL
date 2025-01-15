package com.motivank.kopringjwt.member.entity

import com.motivank.kopringjwt.common.status.Gender
import com.motivank.kopringjwt.common.status.ROLE
import com.motivank.kopringjwt.member.dto.MemberCreateRequest
import com.motivank.kopringjwt.member.dto.MemberResponse
import com.motivank.kopringjwt.member.dto.MemberUpdateRequest
import jakarta.persistence.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])])
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    val loginId: String,

    @Column(nullable = false, length = 100)
    var password: String,

    @Column(nullable = false, length = 10)
    var name: String,

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val birthDate: LocalDate,

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    val gender: Gender,

    @Column(nullable = false, length = 30)
    var email: String,
) {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    val memberRole: List<MemberRole>? = null

    companion object {
        fun of(memberRequest: MemberCreateRequest, encodedPassword: String): Member {
            return Member(
                id = null,
                loginId = memberRequest.loginId,
                password = encodedPassword,
                name = memberRequest.name,
                birthDate = memberRequest.birthDate,
                gender = memberRequest.gender,
                email = memberRequest.email,
            )
        }
    }

    fun update(memberRequest: MemberUpdateRequest) {
        this.name = memberRequest.name
        this.email = memberRequest.email
    }

    fun toDto(): MemberResponse =
        MemberResponse(
            id = id!!,
            loginId = loginId,
            name = name,
            birthDate = birthDate.formatDate(),
            gender = gender.description,
            email = email,
        )

    private fun LocalDate.formatDate(): String =
        this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

}

@Entity
class MemberRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    val role: ROLE,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_member_role_member_id"))
    val member: Member,
) {
    companion object {
        fun of(member: Member): MemberRole {
            return MemberRole(
                id = null,
                role = ROLE.MEMBER,
                member = member,
            )
        }
    }
}