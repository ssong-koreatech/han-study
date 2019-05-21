package com.example.test.reactive

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.toMono

@Component
class CustomerRouter {

    //컨트롤러가 요청 처리하는 것처럼 RouterFunction 을 사용, /functional 경로의 모든 요청 처리
    /*
    @Bean
    fun customerRoutes(): RouterFunction<*> = router{
        "/functional".nest{
            "/customer".nest{
                GET("/"){
                    //it: ServerRequest ->
                    ok().body(Customer(1, "Functional Web").toMono(), Customer::class.java)
                }
            }
        }
    }
     */

    @Autowired
    lateinit var customerHandler: CustomerHandler

    // 새 핸들러 클래스를 만들어서 사용하는 방법
    /*
    @Bean
    fun customerRoutes(): RouterFunction<*> = router{
        "/functional".nest{
            "/customer".nest{
                GET("/"){
                    it: ServerRequest -> customerHandler.get(it)
                }
            }
        }
    }
     */
    @Bean
    fun customerRoutes(): RouterFunction<*> = router{
        "/functional".nest{
            "/customer".nest{
                GET("/{id}", customerHandler::get)
                POST("/", customerHandler::create)
            }
            "/customers".nest{
                GET("/", customerHandler::search)
            }
        }
    }

}

//@Autowired 대신 CustomerRouter 생성자의 일부로 핸들러 삽입
/*
@Component
class CustomerRouter(private val customerHandler: CustomerHandler){
    @Bean
    fun customerRoutes() = router{
        "/functional".nest{
            "/customer".nest{
                GET("/", customerHandler::get)
            }
        }
    }
}
 */


/*****
 * 라우터 : 리액티브 서비스가 응답하는 경로와 메소드 처리
 * 핸들러 : 구체적인 요청을 응답으로 변환
 * 서비스 : 도메인의 비즈니스 로직을 캡슐화
 * 단일 책임 원칙, 느슨한 결합
 *****/




