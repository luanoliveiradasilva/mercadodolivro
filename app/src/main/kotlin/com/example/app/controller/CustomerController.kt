package com.example.app.controller

import com.example.app.controller.request.PostCustomerRequest
import com.example.app.controller.request.PutCustomerRequest
import com.example.app.controller.response.CustomerResponse
import com.example.app.extension.toCustomerModel
import com.example.app.extension.toResponse
import com.example.app.service.CustomerService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("v1/customers")
class CustomerController(
    val customerService: CustomerService
) {


    @ApiOperation(value = "Return list of custumers",  notes = "Should return full customers")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna a lista de customer - OK"),
        ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    ])
    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() }
    }

    @ApiOperation(value = "Create customer with success", notes = "Should create customers")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Cria um customer com sucesso - OK"),
        ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    ])
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @ApiOperation(value = "Return Customer by ID", notes = "Should return customers through of ID")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna  um customer através do ID - OK"),
        ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    ])
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @ApiOperation(value = "Update Customer by ID", notes = "Should update customers through of ID")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Atualiza o customer através do ID - OK"),
        ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    ])
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @ApiOperation(value = "Update status Customer by ID", notes = "Should update status of customers through of ID")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Atualiza o status do customer pelo ID - OK"),
        ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    ])
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}