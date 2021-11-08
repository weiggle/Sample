package com.github.weiggle.jetpack.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.github.weiggle.jetpack.R

class ViewModelActivity : AppCompatActivity() {

    lateinit var myViewModel: MyViewModel
    lateinit var mText: TextView
    lateinit var nomalText: TextView
    lateinit var mBtn: Button
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        mText = findViewById(R.id.text)
        nomalText = findViewById(R.id.normalText)
        mBtn= findViewById(R.id.btn)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.myString.observe(this) {
            mText.text = it
        }
        mBtn.setOnClickListener {
            index++
            myViewModel.setData("this is viewModel===>$index")
            nomalText.text = "this is normal data===>$index"
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("method ======> onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        println("method ======> onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }


    override fun onRetainCustomNonConfigurationInstance(): Any {
        println("method ======> onRetainCustomNonConfigurationInstance")
        return Any()
    }

    override fun getLastNonConfigurationInstance(): Any? {
        println("method ======> getLastNonConfigurationInstance")
        return super.getLastNonConfigurationInstance()
    }

}