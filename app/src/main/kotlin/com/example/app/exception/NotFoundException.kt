package com.example.app.exception

class NotFoundException(override val message: String, val errorCode: String):Exception() {
}