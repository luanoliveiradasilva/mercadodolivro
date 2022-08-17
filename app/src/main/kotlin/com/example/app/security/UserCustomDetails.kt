package com.example.app.security

import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.CustomerStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(private val customerModel: CustomerModel) : UserDetails {

    val id: Int = customerModel.id!!

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return customerModel.roles.map { SimpleGrantedAuthority(it.description) }.toMutableList()
    }

    override fun getPassword(): String = customerModel.password

    override fun getUsername(): String = customerModel.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = customerModel.status == CustomerStatus.ACTIVE

}