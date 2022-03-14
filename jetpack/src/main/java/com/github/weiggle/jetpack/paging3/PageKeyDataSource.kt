package com.github.weiggle.jetpack.paging3



/**
 * @author wei.li
 * @created on 2021/11/24
 * @desc desc
 *
 */
//class PageKeyDataSource(val repository: UserRepository): PagingSource<Int, User>() {
//
//    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
//        return state.anchorPosition?.let {
//            var page = state.closestPageToPosition(it)
//           page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
//
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
//        val nextPagerIndex = params.key ?: 1
//        val response =  repository.getUserList(nextPagerIndex, params.loadSize)
//        return LoadResult.Page(
//            data = response.collect(),
//            prevKey = nextPagerIndex.minus(1),
//            nextKey =
//        )
//    }
//}