package com.example.app.model.customer

import com.example.app.model.enums.CustomerStatus
import com.example.app.model.enums.Roles
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,

    @Column(name = "name_customer")
    var name: String,

    @Column(name = "email_customer")
    var email: String,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column(name = "password")
    var password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Roles::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    var roles: Set<Roles> = setOf()
)