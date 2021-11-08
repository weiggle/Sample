package com.github.weiggle.jetpack.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author wei.li
 * @created on 2021/11/2
 * @desc desc
 *
 */
class MyViewModel : ViewModel() {

    val myString = MutableLiveData<String>()


    fun setData(data: String) {
        myString.value = data
    }

}