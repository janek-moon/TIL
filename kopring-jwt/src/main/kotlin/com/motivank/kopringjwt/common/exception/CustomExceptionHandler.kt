package com.motivank.kopringjwt.common.exception

import com.motivank.kopringjwt.common.dto.BaseResponse
import com.motivank.kopringjwt.common.status.ResultCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun methodArgumentNotValidException(
        e: MethodArgumentNotValidException
    ): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mutableMapOf<String, String>()
        e.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }

        return ResponseEntity(
            BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(InvalidInputException::class)
    protected fun invalidInputException(
        e: InvalidInputException
    ): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf(e.fieldName to (e.message ?: "Not Exception Message"))

        return ResponseEntity(
            BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(BadCredentialsException::class)
    protected fun badCredentialsException(
        e: InvalidInputException
    ): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf("로그인 실패" to "아이디 또는 비밀번호가 일치하지 않습니다.")

        return ResponseEntity(
            BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.message),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    protected fun defaultException(
        e: Exception
    ): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf("미처리 에러" to (e.message ?: "Not Exception Message"))

        return ResponseEntity(
            BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.message),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

}