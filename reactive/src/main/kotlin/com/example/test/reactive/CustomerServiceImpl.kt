package com.example.test.reactive

import com.example.test.reactive.Customer.Telephone
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {
    /* 기존
    companion object {
        val initialCustomers = arrayOf(Customer(1, "Ha"),
                Customer(2, "HSS"),
                Customer(3, "WWE", Telephone("+82", "01012341111")))
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) : Customer? = customers[id]

    override fun searchCustomers(nameFilter: String): List<Customer> =
            customers.filter{
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Int, Customer>::value).toList()
    */

    companion object {
        val initialCustomers = arrayOf(Customer(1, "Ha"),
                Customer(2, "HSS"),
                Customer(3, "WWE", Telephone("+82", "01012341111")))
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    //Customer 객체가 없으면 빈 Mono를 반환하도록 함
    override fun getCustomer(id: Int) = customers[id]?.toMono()?: Mono.empty()

    override fun searchCustomers(nameFilter: String) =
            customers.filter{
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Int, Customer>::value).toFlux()

    /*
    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> {
        return customerMono.subscribe{
            customers[it.id] = it
        }.toMono()
    }
     */

    override fun createCustomer(customerMono: Mono<Customer>): Mono<Customer> =
            customerMono.flatMap {
                if(customers[it.id] == null){
                    customers[it.id] = it
                    it.toMono()
                }else{
                    Mono.error(CustomerExistException("Customer ${it.id} aleady exist!!"))
                }
            }
}