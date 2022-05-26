package com.example.app.repository

import com.example.app.model.book.BookModel
import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.BookStatus
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<BookModel, Int> {
    fun findByStatus(statusBook: BookStatus): List<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
}