package com.example.app.service

import com.example.app.events.PurchaseEvent
import com.example.app.model.purchase.PurchaseModel
import com.example.app.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher

class PurchaseService (
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun create(purchaseModel: PurchaseModel){
        purchaseRepository.save(purchaseModel)

        println("Disparando evento de compra")
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
        println("Finalização do processamento!")
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }
}
