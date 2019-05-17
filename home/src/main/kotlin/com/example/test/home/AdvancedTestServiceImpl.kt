package com.example.test.home

import org.springframework.beans.factory.annotation.Value

class AdvancedTestServiceImpl : TestService{
    @Value(value="\${service.message.text}")
    private lateinit var text: String
    private var count = 1

    override fun testServiceFunc(name: String): String {
        return "test test"
    }


    override fun testServiceFunc2(name: String): String {
        count++
        return "$text $name ($count)"
    }
}