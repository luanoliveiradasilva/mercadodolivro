package com.example.app.repository

import com.example.app.model.customer.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<CustomerModel, Int> {
    fun findByNameContaining(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
}