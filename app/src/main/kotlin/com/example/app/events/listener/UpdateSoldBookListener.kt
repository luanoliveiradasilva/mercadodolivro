package com.example.app.events.listener

import com.example.app.events.PurchaseEvent
import com.example.app.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async

class UpdateSoldBookListener (
    private val bookService: BookService
) {
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        println("Atualizando status dos livros")
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}