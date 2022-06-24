package com.example.app.exception

import com.example.app.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerDevice {

    @ExceptionHandler(NotFoundException::class)
    fun handlerException(exceptionError: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            httpCode = HttpStatus.NOT_FOUND.value(),
            message = exceptionError.message,
            internalCode = exceptionError.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handlerBadRequest(exceptionBadError: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val errorBad = ErrorResponse(
            httpCode = HttpStatus.BAD_REQUEST.value(),
            message = exceptionBadError.message,
            internalCode = exceptionBadError.errorCode,
            null
        )
        return ResponseEntity(errorBad, HttpStatus.BAD_REQUEST)
    }


}