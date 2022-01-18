package com.github.weiggle.pools

import androidx.core.util.Pools

/**
 * @author wei.li
 * @created on 2022/1/12
 * @desc desc
 *
 */
class User(
    var name: String = "",
    var age: Int = 0
) {
}


class UserPoolsHelper {

    companion object {


        var USER_POOL: Pools.SynchronizedPool<User> = Pools.SynchronizedPool<User>(30)

        fun obtain(index: Int): User {
            val acquire = USER_POOL.acquire()
            return if (null == acquire) {
                println("pools has reach=========>$index")
                User(age = index)
            } else {
                println("pools has not  reach=========>${acquire.age} ===========>$index")
                acquire
            }

        }

        fun recycler(user: User) {
            USER_POOL.release(user)
        }
    }

    fun destoryPool() {
//            USER_POOL = null
    }


}