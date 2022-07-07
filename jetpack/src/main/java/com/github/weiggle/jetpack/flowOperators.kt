package com.github.weiggle.jetpack

import androidx.core.util.rangeTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * @author wei.li
 * @created on 2022/6/16
 * @desc desc
 *
 */


fun main() = runBlocking<Unit> {

    val sayHello: () -> Unit = { println(" Hellow world")}

//    sayHello()

//    testReduce()
//
//    testFold()

//    testDebounce()
//    testSample()

//    testFlatmapMerge()

//    testBuffer()
    testCollect()
}


suspend fun testCollect() {

    flow<Int> {
        emit(1)
        delay(50)
        emit(2)
    }.collectLatest {
        print("=======>$it")
        delay(100)
        println("=======>${it}  collectde")
    }
}

suspend fun testBuffer() {
    flowOf("A", "B", "C")
        .onEach { delay(1000) }
        .buffer(2)
        .collect {
            println("===========>${it}")
        }
}

suspend fun testFlatmapMerge() {

    flowOf(1,23,4,5,6,7,7,8,9,9,0)
        .flatMapConcat { flowOf("$it a", "$it b") }
        .collect {
            println("========>$it")
        }
}

suspend fun testSample() {
    flow {
        repeat(10) {
            emit(it)
            delay(50)
        }
    }.sample(100)
        .collect{
            println("=========>$it")
        }
}

suspend fun testDebounce() {
    flow {
        emit(1)
        delay(90)
        emit(2)
        delay(90)
        emit(3)
        delay(1010)
        emit(4)
        delay(1010)
        emit(5)
    }.debounce(1000)
        .collect {
            println("==========>$it")
        }
}

suspend fun testFold() {
    val fold = flowOf(1, 2, 4, 5)
        .fold("hello") { acc, value ->
            println("========>$acc========>$value")
            acc + value
        }
    println("========>$fold")
}

suspend fun testReduce() {
    val reduce = flowOf(1, 2, 3, 4)
        .reduce { accumulator, value ->
            println("=========>${accumulator}==========>${value}")
            accumulator + value
        }
    println("=========>${reduce}")
}