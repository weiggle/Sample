package com.github.weiggle.jetpack.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * @author wei.li
 * @created on 2021/11/2
 * @desc desc
 *
 */
class MyViewModel : ViewModel() {

    val myString = MutableLiveData<String>()
    val mySecond = MutableLiveData<Int>()
    val mediatorLiveData = MediatorLiveData<Int>()

    val state = MutableStateFlow(0)


    fun request()  {
        viewModelScope.launch {
            delay(2000)
            state.value = Random(100).nextInt()
        }
    }

    val source = testSource()

    fun testSource() = liveData<String> {
        emit("this is source")
    }


    fun setData(data: String) {
        myString.value = data
    }

    fun setSecond(value: Int) {
        mySecond.value = value
    }

    fun addSource() {
        mediatorLiveData.addSource(myString) {
            println("addSource=====>${it}")
        }
    }

    fun secondSource() {
        mediatorLiveData.addSource(mySecond) {
            println("secondSource=====>${it.toString()}")
        }
    }

    fun removeSource() {
        mediatorLiveData.removeSource(myString)
    }

    fun removeSecondSource() {
        mediatorLiveData.removeSource(mySecond)
    }

}