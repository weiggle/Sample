package com.github.weiggle.jetpack.paging3

import java.util.*

/**
 * @author wei.li
 * @created on 2021/11/24
 * @desc desc
 *
 */
data class User(val uid: Int, val name: String) {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return Objects.hash(uid, name)
    }
}