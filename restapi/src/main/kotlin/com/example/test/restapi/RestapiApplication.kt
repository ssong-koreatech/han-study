package com.example.test.restapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class RestapiApplication{
	companion object {
	    val initialCustomers = arrayOf(Customer(1, "han"),
				Customer(2, "jung"),
				Customer(3, "seo"))
	}

	@Bean
	fun customers() = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
}

fun main(args: Array<String>) {
	runApplication<RestapiApplication>(*args)
}
