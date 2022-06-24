package com.example.app.model.purchase

import com.example.app.model.book.BookModel
import com.example.app.model.customer.CustomerModel
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "purchase")
data class PurchaseModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: CustomerModel,

    @ManyToMany
    @JoinTable(name = "purchase_book",
    joinColumns = [JoinColumn(name = "purchase_id")],
    inverseJoinColumns = [JoinColumn(name = "book_id")])
    val book: List<BookModel>,

    @Column
    val notaFiscal: String? = null,

    @Column
    val price: BigDecimal,

    @Column(name = "created_at")
    val creteAt: LocalDateTime

)
