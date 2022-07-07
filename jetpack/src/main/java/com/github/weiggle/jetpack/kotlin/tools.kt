package com.github.weiggle.jetpack.kotlin

/**
 * @author wei.li
 * @created on 2022/6/16
 * @desc desc
 *
 */

fun main() {

    val times10 = magnitude(10)
    val times50 = magnitude(50)
    println(times10(5))
    println(times50(2))
}

fun magnitude(times: Int): (Int) -> Int {
    return { it * times}
}