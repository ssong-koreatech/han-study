package com.example.test.springdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringdataApplication

//작성 순서
//Repository -> Service Interface -> Service -> Handler -> Route

fun main(args: Array<String>) {
	runApplication<SpringdataApplication>(*args)
}
