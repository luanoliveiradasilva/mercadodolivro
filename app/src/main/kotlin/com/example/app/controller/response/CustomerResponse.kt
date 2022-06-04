package com.example.app.controller.response

import com.example.app.model.enums.CustomerStatus

data class CustomerResponse(
    val id: Int? = null,
    val name: String,
    val email: String,
    val status: CustomerStatus
)