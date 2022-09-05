package com.example.app.controller

import com.example.app.controller.request.PostBookRequest
import com.example.app.controller.request.PutBookRequest
import com.example.app.controller.response.BookResponse
import com.example.app.extension.toBookModel
import com.example.app.extension.toResponse
import com.example.app.service.BookService
import com.example.app.service.CustomerService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("v1/books")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @ApiOperation("Create books")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Create the books success - OK"),
            ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            ApiResponse(code = 500, message = "Foi gerada uma exceção"),
        ]
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @ApiOperation("Return list books")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Return the books success - OK"),
            ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            ApiResponse(code = 500, message = "Foi gerada uma exceção"),
        ]
    )
    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findAll(pageable).map { it.toResponse() }
    }

    @ApiOperation("Return actives of books")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Return actives of books success - OK"),
            ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            ApiResponse(code = 500, message = "Foi gerada uma exceção"),
        ]
    )
    @GetMapping("/actives")
    fun findActive(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> =
        bookService.findActive(pageable).map { it.toResponse() }

    @ApiOperation("Return the books through ID ")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Return the books success - OK"),
            ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            ApiResponse(code = 500, message = "Foi gerada uma exceção"),
        ]
    )
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @ApiOperation("Update status books through ID ")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Update status of books success - OK"),
            ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            ApiResponse(code = 500, message = "Foi gerada uma exceção"),
        ]
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @ApiOperation("Update status books through ID ")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Update status of books success - OK"),
            ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            ApiResponse(code = 500, message = "Foi gerada uma exceção"),
        ]
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

}