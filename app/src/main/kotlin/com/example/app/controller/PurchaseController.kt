package com.example.app.controller

import com.example.app.controller.mappers.PurchaseMapper
import com.example.app.controller.request.PostPurchaseRequest
import com.example.app.service.PurchaseService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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

    @ApiOperation("Create purchase")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Create the purchase success - OK"),
            ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            ApiResponse(code = 500, message = "Foi gerada uma exceção"),
        ]
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest){
        purchaseService.create(purchaseMapper.toModel(request))
    }
}