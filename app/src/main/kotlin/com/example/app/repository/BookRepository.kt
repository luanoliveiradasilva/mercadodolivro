package com.example.app.repository

import com.example.app.model.book.BookModel
import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.BookStatus
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
interface BookRepository : JpaRepository<BookModel, Int> {
    fun findByStatus(statusBook: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
}