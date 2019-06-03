package com.example.test.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class CloudApplication

fun main(args: Array<String>) {
	runApplication<CloudApplication>(*args)
}