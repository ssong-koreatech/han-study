package com.example.test.restapi



//JsonInclude 어노테이션을 붙이면 NULL 인 것은 직렬화안할 수 있음.
//이렇게 일일히 클래스마다 안붙이고 yml에서 설정해줄 수 있음
//@JsonInclude(JsonInclude.Include.NON_NULL)
data class Person (var id: Int = 0, var name: String = "", var telephone: Telephone? = null){   //전화번호는 null 가능
    data class Telephone(var contryConde: String = "", var telephoneNumber: String="")
}
