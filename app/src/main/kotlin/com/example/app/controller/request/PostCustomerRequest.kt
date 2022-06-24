package com.example.app.controller.request

import com.example.app.model.enums.CustomerStatus
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty
    var name: String,
    @field:Email
    var email: String,
    var status: CustomerStatus
)
