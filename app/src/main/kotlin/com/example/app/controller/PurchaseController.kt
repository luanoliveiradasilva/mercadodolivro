package com.example.app.controller

import com.example.app.controller.mappers.PurchaseMapper
import com.example.app.controller.request.PostPurchaseRequest
import com.example.app.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("v1/purchases")
class PurchaseController(
    val purchaseMapper: PurchaseMapper,
    val purchaseService: PurchaseService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest){
        purchaseService.create(purchaseMapper.toModel(request))
    }
}