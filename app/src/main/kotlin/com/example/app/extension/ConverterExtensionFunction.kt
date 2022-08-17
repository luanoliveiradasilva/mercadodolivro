package com.example.app.extension

import com.example.app.controller.request.PostBookRequest
import com.example.app.controller.request.PostCustomerRequest
import com.example.app.controller.request.PutBookRequest
import com.example.app.controller.request.PutCustomerRequest
import com.example.app.controller.response.BookResponse
import com.example.app.controller.response.CustomerResponse
import com.example.app.model.book.BookModel
import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.BookStatus
import com.example.app.model.enums.CustomerStatus

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(
        name = this.name,
        email = this.email,
        status = CustomerStatus.ACTIVE,
        password = this.password
    )
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(
        id = previousValue.id,
        name = this.name,
        email = this.email,
        status = previousValue.status,
        password = previousValue.password
    )
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        nameBook = this.name,
        price = this.price,
        status = BookStatus.ACTIVE,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        idBook = previousValue.idBook,
        nameBook = this.name ?: previousValue.nameBook,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.idBook,
        name = this.nameBook,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}