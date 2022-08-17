package com.example.app.service

import com.example.app.exception.AuthenticationException
import com.example.app.repository.CustomerRepository
import com.example.app.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val customerRepository: CustomerRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val customer =
            customerRepository.findById(id.toInt()).orElseThrow { (AuthenticationException("Use not found", "1234")) }
        return UserCustomDetails(customer)
    }
}