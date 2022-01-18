package com.github.weiggle.pools

/**
 * @author wei.li
 * @created on 2022/1/12
 * @desc desc
 *
 */

fun main() {

//    val users = mutableListOf<User>()
//
//    repeat(52) {
//        val user = UserPoolsHelper.obtain(it)
//        users.add(user)
//    }
//    users.forEach {
//        UserPoolsHelper.recycler(it)
//    }
//    println("divider=============》")
//
//    repeat(57) {
//        val user = UserPoolsHelper.obtain(it*2)
//    }

    val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
    for (index in 1..list.size) {
        println("item =======>$index")
    }


//    val conHandlerA = ConHandlerA()
//    val conHandlerB = ConHandlerB()
//    conHandlerA.next = conHandlerB
//    conHandlerA.handleRequest("C")


    val interceptor = InterceptorClient()
    interceptor.addInterceptor(AInterceptor())
    interceptor.addInterceptor(BInterceptor())
    interceptor.addInterceptor(CInterceptor())
    interceptor.addInterceptor(DInterceptor())
    interceptor.process("D")
}

class InterceptorClient {
    var interceptors = mutableListOf<Interceptor>()

    fun addInterceptor(interceptor: Interceptor): InterceptorClient {
        interceptors.add(interceptor)
        return this
    }

    fun process(request: String) {
        interceptors.forEach {
            val process = it.process(request)
        }
    }
}

abstract class Interceptor {
    abstract fun process(request: String): Boolean
}

class AInterceptor : Interceptor() {
    override fun process(request: String): Boolean {
        return if (request == "A") {
            println("this is process by A")
            true
        } else {
            println("this is not process by A")
            false
        }
    }
}

class BInterceptor : Interceptor() {
    override fun process(request: String): Boolean {
        return if (request == "B") {
            println("this is process by B")
            true
        } else {
            println("this is not process by B")
            false
        }
    }
}

class CInterceptor : Interceptor() {
    override fun process(request: String): Boolean {
        return if (request == "C") {
            println("this is process by C")
            true
        } else {
            println("this is not process by C")
            false
        }
    }
}

class DInterceptor : Interceptor() {
    override fun process(request: String): Boolean {
        return if (request == "D") {
            println("this is process by D")
            true
        } else {
            println("this is not process by D")
            false
        }
    }
}


abstract class Handler {
    var next: Handler? = null

    abstract fun handleRequest(request: String)
}

class ConHandlerA : Handler() {

    override fun handleRequest(request: String) {
        if (request == ("A")) {
            println("A handler this request")
        } else {
            next?.handleRequest(request) ?: println(
                "no par handler this reqeust"
            )
        }
    }
}

class ConHandlerB : Handler() {
    override fun handleRequest(request: String) {
        if (request == "B") {
            println("B handler this request ")
        } else {
            next?.handleRequest(request) ?: println(
                "B has no more handler this request"
            )
        }
    }
}