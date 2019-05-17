package com.example.test.home

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


//@Service
class TestServiceImpl : TestService{
    override fun testServiceFunc(name : String) = "hello $name"

    @Value(value = "\${service.message.text}")
    private lateinit var text: String
    override fun testServiceFunc2(name : String) = "${text} $name"
}