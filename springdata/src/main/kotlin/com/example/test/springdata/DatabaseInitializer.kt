package com.example.test.springdata

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class DatabaseInitializer {

    //ReactiveMongoOperations 는 스프링데이터가 제공함, 몽고db한테 명령을 보낼 때 리액티브하게 보낼 수 있게 해줌.
    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    @Autowired
    lateinit var customerRepository: CustomerRepository

    companion object {
        val initialCustomers = listOf(Customer(1, "han"),
                Customer(2, "ha2"),
                Customer(3, "wWW", Customer.Telephone("+82", "0101002938")))
    }

    //컬렉션, 레포지토리
    /*
    @PostConstruct
    fun initData(){
        //몽고db 서버에게 Customers 컬렉션을 생성하라고 하는 거
        mongoOperations.collectionExists("Customers").subscribe{
            if(it != true) mongoOperations
                    .createCollection("Customers")
                    .subscribe { println("Customers collections created!") }
            else{
                println("Customers collections already exist!")
            }
        }
        customerRepository.saveAll(initialCustomers).subscribe{
            println("Default customers created!")
        }
    }
     */

    //레포지토리가 컬렉션 자체를 처리하기 때문에 위의 코드처럼 컬렉션 코드를 굳이 써주지 않아도됨
    @PostConstruct
    fun initData(){
        customerRepository.saveAll(initialCustomers).subscribe{
            println("Default customers created!")
        }
    }
}