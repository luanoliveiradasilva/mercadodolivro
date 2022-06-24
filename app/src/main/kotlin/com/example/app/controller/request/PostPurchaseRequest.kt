package com.example.app.controller.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PostPurchaseRequest (
    @field:NotNull
    @field:Positive
    val customerId:Int,

    @field:NotNull
    val nookIds: Set<Int>
    )
