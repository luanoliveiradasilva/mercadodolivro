package com.example.app.model

import javax.persistence.*

@Entity(name = "consumers")
class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String
)