package com.example.app.model

import com.example.app.model.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*


@Entity(name = "book")
class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id_book: Int,

    @Column(name = "name_book")
    var nameBook: String,

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "status_book")
    @Enumerated(EnumType.STRING)
    var statusBook: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

)