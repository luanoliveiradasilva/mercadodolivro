package com.example.app.service

import com.example.app.exception.NotFoundException
import com.example.app.model.book.BookModel
import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.BookStatus
import com.example.app.model.enums.Errors
import com.example.app.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActive(pageable: Pageable): Page<BookModel> {
       return bookRepository.findByStatus(BookStatus.ACTIVE, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow { NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code) }
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