package com.github.weiggle.jetpack.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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

    val countDownFlow = flow<Int> {
        val startValue = 10
        var currentValue = startValue
        emit(currentValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    fun incrementCounter() {
        _stateFlow.value= stateFlow.value +1
    }


    private val _sharedGlow = MutableSharedFlow<Int>(0)
    val sharedFlow = _sharedGlow.asSharedFlow()



xxx
    val firsetName = flow<String> { emit("Ahmad") }

    init {
       viewModelScope.launch {
           sharedFlow.collect {
               delay(2000L)
               "fjew".run {

               }
               println("flow ==========> $it")
           }
       }
    }

     fun ss(number:Int) {
         viewModelScope.launch {
             _sharedGlow.emit(number * number)
         }
    }

    fun request() {
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

    fun setSecond(value: Int) xxx{
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