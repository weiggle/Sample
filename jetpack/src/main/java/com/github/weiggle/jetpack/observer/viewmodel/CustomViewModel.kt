package com.github.weiggle.jetpack.observer.viewmodel

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author wei.li
 * @created on 2021/11/9
 * @desc desc
 *
 */
class CustomViewModel : ViewModel() {

    val myLiveData = MutableLiveData<String>()

    fun setData(data: String) {
        myLiveData.value = data
    }


}