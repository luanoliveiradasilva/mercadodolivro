package com.example.app.service

import com.example.app.model.book.BookModel
import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.BookStatus
import com.example.app.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(): List<BookModel> {
        return bookRepository.findAll().toList()
    }

    fun findActive(): List<BookModel> {
       return bookRepository.findByStatus(BookStatus.ACTIVE)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CALLED

        update(book)
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        var books = bookRepository.findByCustomer(customer)
        for(book in books)
            book.status = BookStatus.DELETED
        bookRepository.saveAll(books)
    }


}