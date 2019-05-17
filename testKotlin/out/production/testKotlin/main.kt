fun sum(a: Int, b: Int): Int{
    return a + b
}
fun sum2(a: Int, b: Int) = a + b

fun sub(a: Int, b: Int) = a - b

//자바의 void => 코틀린의 Unit
fun printKotlin() : Unit{
    println("hello Kotlin!")
}
// Unit 생략 가능
fun printkotiln2() {
    println("hello Kotlin!")
}


//val 값의 할당이 1회만 가능, final 과 유사, 자료형 안쓰면 알아서 type 추론
//var 일반 변수
val han : Int = 1
var han2 = "hanyugyeong"

var a = 1
val s1 = "a is $a"

val s2 = s1.replace("is", "was")

//조건문
fun maxOf(a: Int, b: Int): Int{
    if(a > b){
        return a
    } else{
        return b
    }
}

fun maxOf2(a: Int, b: Int) = if(a > b) a else b


//nullable
//fun parseInt(str: String) : Int?{
    //정수가 아닌 경우에 null 을 리턴
//}
//nullable 타입의 변수를 접근할 때는 반드시 null 체크를 해줘야함

//코틀린에서 최상위 타입은 Any 임. 자바의 Object 랑 비슷한 개념


//while 은 자바랑 같다라고 보면 됨

//자바의 switch 랑 비슷한 것은 when
fun describe(obj: Any) : String =
        when(obj){
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }


//in 연산자를 이용하여 숫자 범위를 체크 가능
fun loopFun(){
    val x = 3
    if(x in 1..10) println("fits in range")
    for(x2 in 1..5) print(x)

    //listOf
    val items = listOf("a", "b", "c")
    for(i in items) println(i)
}

//코틀린에서는 모든 것이 객체임
// === : 같은 객체이냐?


//기본 타입
//Double(64), Float(32), Long(64), Int(32), Short(16), Byte(8)
//코틀린은 char에 숫자를 절대 비교할 수 없음. 예를 들면, 자바는 a = 96 값인데, 코틀린은 절대 안됨

//underscore
//1_000_000 긴 숫자에 언더바를 붙임

fun test(){
    val a = 10000
    val b = 10000
    println("a===b: ${a === b}")    //true
    println("a==b: ${a==b}")        //true

    var c = 10000              //primitive type
    var d: Int? = 10000             //object type
    //println("c===d: ${c === d}")    //false
    println("c==d: ${c==d}")        //true
}


//배열 생성방법
//1.
val arr = Array(5, {i -> i.toString()})
//2.
val arr2 = arrayOf("1", "2", "3", "4", "5")



fun main(){
    println(sum(1,2))
}




