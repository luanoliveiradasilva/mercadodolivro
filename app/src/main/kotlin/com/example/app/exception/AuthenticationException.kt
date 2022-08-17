package com.example.app.exception

class AuthenticationException(override val message: String, val errorCode: String):Exception() {
}