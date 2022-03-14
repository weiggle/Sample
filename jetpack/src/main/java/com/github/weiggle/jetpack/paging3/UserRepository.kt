package com.github.weiggle.jetpack.paging3

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author wei.li
 * @created on 2021/11/24
 * @desc desc
 *
 */
class UserRepository {

    suspend fun getUserList(pageIndex: Int, pageSize: Int): Flow<List<User>> {
        return flow {  }
    }
}