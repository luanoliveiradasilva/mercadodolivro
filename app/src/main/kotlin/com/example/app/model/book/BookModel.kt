package com.example.app.model.book

import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*


@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idBook: Int? = null,

    @Column(name = "name_book")
    var nameBook: String,

    @Column(name = "price")
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
) {


    @Column(name = "status_book")
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.CALLED || field == BookStatus.DELETED)
                throw Exception("Cannot change ${field} book}")
            field = value
        }

    constructor(
        idBook: Int? = null,
        nameBook: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?
    ) : this(idBook, nameBook, price, customer) {
        this.status = status
    }
}