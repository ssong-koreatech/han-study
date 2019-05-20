package com.example.test.reactive

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {

    /* 기존
    fun getCustomer(id: Int) : Customer?
    fun searchCustomers(nameFilter: String) : List<Customer>
     */

    //Mono 사용하여 단일 Customer의 게시자를 반환하도록 서비스 수정, 플럭스는 결과를 여러개 반환
    fun getCustomer(id: Int) : Mono<Customer>?
    fun searchCustomers(nameFilter: String) : Flux<Customer>
}