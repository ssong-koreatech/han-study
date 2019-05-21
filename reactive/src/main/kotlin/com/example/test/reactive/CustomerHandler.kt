package com.example.test.reactive

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.onErrorResume
import java.net.URI

/*
@Component
class CustomerHandler {

    /*
    fun get(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().body(Customer(1, "functional web").toMono(),
                Customer::class.java)
    }
     */

    //위의 함수랑 똑같은데 반환형을 안써주고 = 으로 쓰는걸 코틀린의 타입 추론이라고 함.
    fun get(serverRequest: ServerRequest)
            = ok().body(Customer(1, "functional web").toMono(),
                Customer::class.java)

}
 */

@Component
class CustomerHandler(val customerService: CustomerService){
    fun get(serverRequest: ServerRequest) =
            customerService.getCustomer(serverRequest.pathVariable("id").toInt())
                    .flatMap { ok().body(fromObject(it)) }
                    //.switchIfEmpty(notFound().build())
                    .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun search(serverRequest: ServerRequest) =
            ok().body(customerService.searchCustomers(serverRequest
                            .queryParam("nameFilter")
                            .orElse("")),
                    Customer::class.java)

    fun create(serverRequest: ServerRequest) =
            customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
                //status(HttpStatus.CREATED).body(fromObject(it))
                created(URI.create("/functional/customer/${it.id}")).build()
            }.onErrorResume(Exception::class){
                badRequest().body(
                        fromObject(
                            ErrorResponse("error creating customer", it.message ?: "error")
                        )
                )
            }
}