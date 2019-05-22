package com.example.test.springdata

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CustomerRepository: ReactiveCrudRepository<Customer, Int>