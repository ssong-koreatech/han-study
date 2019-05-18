package com.example.test.restapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    @RequestMapping(value=["/customer/test"], method=arrayOf(RequestMethod.GET))
    fun getCustomer() = "customer from a controller"


    //json 형식의 데이터로 응답해줌
    @RequestMapping(value=["/customer/test_json"], method=arrayOf(RequestMethod.GET))
    fun getCustomer2() = Customer(1, "Kotlin")

    @Autowired
    lateinit var customers : ConcurrentHashMap<Int, Customer>

    //** 경로 변수
    //localhost:8080/customer/1 이런식으로 url 요청
    @RequestMapping(value=["/customer/{id}"], method=arrayOf(RequestMethod.GET))
    fun getCustomer3(@PathVariable id : Int) = customers[id]

    @RequestMapping(value=["/customers_all"], method=arrayOf(RequestMethod.GET))
    fun getCustomer4() = customers.map(Map.Entry<Int, Customer>::value).toList()

    //** 요청 매개변수
    //매개변수가 반드시 필요한지 required---매개변수가 없을 때는 디폴트로 defaultValue, 이 때 이 디폴트값은
    //문자열이어야하고, 스프링이 나중에 알아서 타입을 자동으로 변환시켜준다고 함.
    //RequestParam::매개변수를 통해 받음, PathVariable::url을 통해 매개변수를 받아서 처리
    //RequstParams의 경우 요청 url에 매개변수 값을 넣어줌
    //localhost:8080/customers?nameFilter=### 이런식으로 url 요청
    @RequestMapping(value=["/customers"], method=arrayOf(RequestMethod.GET))
    fun getCustomer5(@RequestParam(required=false, defaultValue="") nameFilter: String)
            = customers.filter {
        it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toList()

}