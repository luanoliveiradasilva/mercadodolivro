package com.example.app.repository

import com.example.app.model.purchase.PurchaseModel
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRepository: JpaRepository<PurchaseModel, Int> {

}
