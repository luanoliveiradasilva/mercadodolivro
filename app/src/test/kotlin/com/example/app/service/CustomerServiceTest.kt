package com.example.app.service

import com.example.app.exception.NotFoundException
import com.example.app.model.customer.CustomerModel
import com.example.app.model.enums.CustomerStatus
import com.example.app.model.enums.Roles
import com.example.app.repository.CustomerRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import kotlin.random.Random

@ExtendWith(MockKExtension::class)
internal class CustomerServiceTest {


    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var bookService: BookService

    @MockK
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @InjectMockKs
    private lateinit var customerService: CustomerService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should list all customer`() {
        //Given
        val expected = buildCustomerModel()

        //When
        every { customerRepository.findAll() } returns expected

        val result = customerService.getAll(null)

        //Then
        Assertions.assertEquals(result, expected)
        verify(exactly = 1) { customerRepository.findAll() }

    }

    @Test
    fun `should find customer by name`() {
        //Given
        val name = "nome"
        val expected = buildCustomerModel()

        //When
        every { customerRepository.findByNameContaining(name) } returns expected

        val result = customerService.getAll(name)

        //Then
        Assertions.assertEquals(result, expected)
        verify(exactly = 1) { customerRepository.findByNameContaining(name) }

    }

    @Test
    fun `should find customer by id`() {
        // Given
        val id = 2
        val expected = CustomerModel(
            id = id,
            name = "Nome",
            email = "nome@email.com",
            status = CustomerStatus.ACTIVE,
            password = UUID.randomUUID().toString()
        )

        every { customerRepository.findById(id) } returns Optional.of(expected)

        // When
        val result = customerService.findById(id)

        // Then
        Assertions.assertEquals(result, expected)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should customer throw exception`() {
        // Given
        val id = 2

        every { customerRepository.findById(id) } returns Optional.empty()

        // When
        val errorResult = assertThrows<NotFoundException> {
            customerService.findById(id)
        }

        // Then
        Assertions.assertEquals("Customer [${id}] not exists", errorResult.message)
        Assertions.assertEquals("ML-201", errorResult.errorCode)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `Should create customer and encrypt password`() {
        // Given
        val password = Random.nextInt().toString()
        val actual = CustomerModel(
            id = 1,
            name = "Nome",
            email = "nome@email.com",
            status = CustomerStatus.ACTIVE,
            password = password,
            roles = setOf(Roles.CUSTOMER)
        )
        val passwordFake = UUID.randomUUID().toString()
        val passwordEncode = actual.copy(password = passwordFake)


        every { customerRepository.save(passwordEncode) } returns actual
        every { bCryptPasswordEncoder.encode(password) } returns passwordFake

        // When
        customerService.create(actual)

        // Then
        verify(exactly = 1) { customerRepository.save(passwordEncode) }
        verify(exactly = 1) { bCryptPasswordEncoder.encode(password) }
    }

    @Test
    fun `should update customer`() {
        // Given
        val customerExpected = CustomerModel(
            id = 1,
            name = "Nome",
            email = "nome@email.com",
            status = CustomerStatus.ACTIVE,
            password = "123456",
            roles = setOf(Roles.CUSTOMER)
        )

        every { customerRepository.existsById(customerExpected.id!!) } returns true
        every { customerRepository.save(customerExpected) } returns customerExpected

        // When
        customerService.update(customerExpected)

        // Then
        verify(exactly = 1) { customerRepository.existsById(customerExpected.id!!) }
        verify(exactly = 1) { customerRepository.save(customerExpected) }
    }


    @Test
    fun `should throw exception update customer`() {
        // Given
        val customerExpected = CustomerModel(
            id = 1,
            name = "Nome",
            email = "nome@email.com",
            status = CustomerStatus.ACTIVE,
            password = "123456",
            roles = setOf(Roles.CUSTOMER)
        )

        every { customerRepository.existsById(customerExpected.id!!) } returns false
        every { customerRepository.save(customerExpected) } returns customerExpected

        // When

        assertThrows<Exception> {
            customerService.update(customerExpected)
        }


        // Then
        verify(exactly = 1) { customerRepository.existsById(customerExpected.id!!) }
        verify(exactly = 0) { customerRepository.save(customerExpected) }
    }

    @Test
    fun `should delete customer by id`() {
        // Given
        val id = 1
        val customerExpected = CustomerModel(
            id = 1,
            name = "Nome",
            email = "nome@email.com",
            status = CustomerStatus.ACTIVE,
            password = "123456",
            roles = setOf(Roles.CUSTOMER)
        )

        every { customerRepository.findById(id)} returns Optional.of(customerExpected)
        every { bookService.deleteByCustomer(customerExpected) } returns Unit
        every { customerRepository.save(customerExpected) } returns customerExpected

        // When
        customerService.delete(id)

        // Then
        verify(exactly = 1) { bookService.deleteByCustomer(customerExpected)  }
        verify(exactly = 1) { customerRepository.save(customerExpected) }
    }


    @Test
    fun `Should available Email customer`(){
        // Given
        val email = "teste@teste.com"

        every { customerRepository.existsByEmail(email) } returns true

        // When
        val result = customerService.emailAvailable(email)

        // Then
        Assertions.assertTrue(result)
        verify(exactly = 1) { customerRepository.existsByEmail(email) }
    }

    private fun buildCustomerModel() = listOf(
        CustomerModel(
            id = 1,
            name = "nome",
            email = "luan@email.com",
            status = CustomerStatus.ACTIVE,
            password = UUID.randomUUID().toString()
        ),
        CustomerModel(
            id = 2,
            name = "nome 1",
            email = "luan@email.com",
            status = CustomerStatus.INACTIVE,
            password = UUID.randomUUID().toString()
        )
    )
}
