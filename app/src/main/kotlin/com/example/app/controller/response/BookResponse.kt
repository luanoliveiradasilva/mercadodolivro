package com.example.app.controller.response

import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.BookStatus
import java.math.BigDecimal

data class BookResponse (
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BookStatus? = null
)
