package com.example.app.exception

class BadRequestException(override val message: String, val errorCode: String):Exception() {
}