package com.github.weiggle.jetpack.observer

import androidx.lifecycle.*
import com.github.weiggle.jetpack.observer.viewmodel.CustomViewModel

/**
 * @author wei.li
 * @created on 2021/11/9
 * @desc desc
 *
 */
class CustomLifeOwner : LifecycleOwner, ViewModelStoreOwner {
    private val lifecycleRegister = LifecycleRegistry(this)
    private val viewModelStore = ViewModelStore()

    lateinit var viewModel: CustomViewModel

    init {
        lifecycleRegister.currentState = Lifecycle.State.CREATED
    }

    override fun getViewModelStore(): ViewModelStore {
        return viewModelStore
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegister
    }

    fun setData(data: String) {
        viewModel.setData(data)
    }

    fun show() {
        lifecycleRegister.currentState = Lifecycle.State.RESUMED
        viewModel = ViewModelProvider(this).get(CustomViewModel::class.java)
        viewModel.myLiveData.observe(this, Observer {
            println("method ======CustomLifeOwner=====> $it")
        })
    }

    fun dismiss() {
        lifecycleRegister.currentState = Lifecycle.State.DESTROYED
    }

}