package com.example.test.reactive

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class CustomerController {

    /* 기존
    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping(value=["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Customer?> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @GetMapping(value=["/customers"])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customerService.searchCustomers(nameFilter)
     */

    //모노는 결과를 하나만 반환해야 하는 경우 사용, 플럭스는 결과를 0-n 개 반환하는 경우에 사용
    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping(value=["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Mono<Customer>> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @GetMapping(value=["/customers"])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customerService.searchCustomers(nameFilter)

    @PostMapping(value=["/customer/"])
    fun createCustomer(@RequestBody customerMono: Mono<Customer>) =
            ResponseEntity(customerService.createCustomer(customerMono),
                    HttpStatus.CREATED)
}