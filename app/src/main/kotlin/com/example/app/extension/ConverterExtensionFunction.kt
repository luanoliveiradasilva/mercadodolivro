package com.example.app.extension

import com.example.app.controller.request.PostCustomerRequest
import com.example.app.controller.request.PutCustomerRequest
import com.example.app.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
   return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel{
    return CustomerModel(id = id, name = this.name, email = this.email)
}