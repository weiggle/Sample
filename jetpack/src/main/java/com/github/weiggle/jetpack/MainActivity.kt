package com.github.weiggle.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import com.github.weiggle.jetpack.databinding.ActivityMainBinding
import com.github.weiggle.jetpack.fragmentresult.FragmentA
import com.github.weiggle.jetpack.observer.viewmodel.CustomViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CustomViewModel
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager.beginTransaction()
            .replace(viewBinding.fragmentContainer.id, FragmentA())
            .commitAllowingStateLoss()
    }
}