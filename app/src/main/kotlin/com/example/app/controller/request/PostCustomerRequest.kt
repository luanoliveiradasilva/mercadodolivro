package com.example.app.controller.request

import com.example.app.model.enums.CustomerStatus

data class PostCustomerRequest(
    var name: String,
    var email: String,
    var status: CustomerStatus
)
