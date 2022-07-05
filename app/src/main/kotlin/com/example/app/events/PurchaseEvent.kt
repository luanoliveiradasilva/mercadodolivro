package com.example.app.events

import com.example.app.model.purchase.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    source: Any,
    val purchaseModel: PurchaseModel
) : ApplicationEvent(source)
