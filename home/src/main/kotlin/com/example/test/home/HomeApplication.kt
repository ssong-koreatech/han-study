package com.example.test.home


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class HomeApplication{
	@Bean
	@ConditionalOnExpression("#{'\${service.message.type}' == 'simple'}")
	fun beanServiceFunc() : TestService = TestServiceImpl()

	@Bean
	@ConditionalOnExpression("#{'\${service.message.type}'=='advance'}")
	fun beanAdvancedServiceFunc() : TestService = AdvancedTestServiceImpl()

}

/*
@RestController	//RestController 는 ResponseBody 가 없어도 데이터 형식으로 반환해주는 역할을 한다
class FirstController(val testService: TestService){

	@RequestMapping(value=["/first"], method=arrayOf(RequestMethod.GET))
	fun hello() = "hello world!"

	@RequestMapping(value=["/first/{name}"], method= arrayOf(RequestMethod.GET))
	fun testFunc(@PathVariable name: String) = testService.testServiceFunc(name)
}

@Controller
class SecondController{

	@Autowired
	lateinit var testService: TestService

	@RequestMapping(value=["/third/{name}"], method= arrayOf(RequestMethod.GET))
	@ResponseBody
	fun testFunc2(@PathVariable name: String) = testService.testServiceFunc(name)

	@Autowired
	lateinit var service: TestServiceImpl    //인터페이스, 서비스 작동 방식을 보여주지 않음.
	@RequestMapping(value=["/fourth/{name}"], method= arrayOf(RequestMethod.GET))
	@ResponseBody
	fun testFunc3(@PathVariable name: String) = service.testServiceFunc(name)


	@RequestMapping(value=["/second"], method=arrayOf(RequestMethod.GET))
	@ResponseBody		//ResponseBody는 데이터 형식으로 반환해주는 역할을 한다.
	fun hello2() = "hello world22222222!"
}
 */

@RestController
class ThirdController{

	@Autowired
	lateinit var testService: TestService

	@RequestMapping(value=["/fifth/{name}"], method= arrayOf(RequestMethod.GET))
	fun testFunc(@PathVariable name: String) = testService.testServiceFunc2(name)


}





fun main(args: Array<String>) {
	runApplication<HomeApplication>(*args)
}