package com.motivank.kopringjwt.common.authority

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

const val EXPIRATION_MILLISECONDS: Long = 1000 * 60 * 60 * 24 * 7

@Component
class JwtTokenProvider {

    val log: Logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)) }

    fun createToken(authentication: Authentication): TokenInfo {
        val authorities: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)

        val now = Date()
        val accessExpiration = Date(now.time + EXPIRATION_MILLISECONDS)

        val accessToken = Jwts
            .builder()
            .subject(authentication.name)
            .claim("auth", authorities)
            .issuedAt(now)
            .expiration(accessExpiration)
            .signWith(key)
            .compact()

        return TokenInfo("Bearer", accessToken)
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        val auth = claims["auth"] ?: throw RuntimeException("잘못된 토큰입니다.")

        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal: UserDetails = User(claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun validateToken(token: String): Boolean {
        try {
            getClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> {} // Invalid JWT Token
                is MalformedJwtException -> {} // Invalid JWT Token
                is ExpiredJwtException -> {} // Expired JWT Token
                is UnsupportedJwtException -> {} // Unsupported JWT Token
                is IllegalArgumentException -> {} // JWT claims string is empty.
                else -> {} // Unknown Exception
            }
            log.error(e.message)
        }
        return false
    }

    private fun getClaims(token: String): Claims =
        Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(key.encoded))
            .build()
            .parseSignedClaims(token)
            .payload

}