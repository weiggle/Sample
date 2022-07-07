package io.github.algorithm.sparse

import java.lang.String.format

/**
 * @author wei.li
 * @created on 2022/6/27
 * @desc 稀疏数组
 *
 */

fun main() {
    val list = Array(11) { IntArray(11) }
    list[3][4] = 1
    list[4][5] = 2
    list[5][6] = 1
    var sum = 0;

    list.forEachIndexed { index, ints ->
        ints.forEach {
            print("${it}\t")
        }
        println("")
    }
    list.forEachIndexed { index, ints ->
        sum += ints.count { it != 0 }
    }
    println("sum===>${sum}")

    val sparseArray = Array(sum + 1) { IntArray(3) }
    sparseArray[0][0] = list[0].size
    sparseArray[0][1] = list[1].size
    sparseArray[0][2] = sum
    var line = 0
    list.forEachIndexed { index, ints ->
        ints.forEachIndexed { index2, item ->
            if (item != 0) {
                line++
                sparseArray[line][0] = index
                sparseArray[line][1] = index2
                sparseArray[line][2] = item
            }
        }
    }

    println("===========>hash")
    sparseArray.forEachIndexed { index, ints ->
        ints.forEach {
            print("${it}\t")
        }
        println("")
    }


    println("===========>revers hash")

    val t = Array(sparseArray[0][0]) { IntArray(sparseArray[0][1]) }

    sparseArray.forEachIndexed { index, ints ->
        if (index > 0) {
            t[ints[0]][ints[1]] = ints[2]
        }
    }

    t.forEachIndexed { index, ints ->
        ints.forEach {
            print("${it}\t")
        }
        println("")
    }

}
