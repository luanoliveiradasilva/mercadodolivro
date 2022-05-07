package com.example.app.repository

import com.example.app.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<CustomerModel, Int>{

    fun findByNameContaining(name: String): List<CustomerModel>

}