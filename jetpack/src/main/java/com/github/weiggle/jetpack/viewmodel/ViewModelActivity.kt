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
        mBtn = findViewById(R.id.btn)
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


    /**
     *
     * @author weiggle
     * @date 2021/11/9 22:00
     * @param null
     * @return null
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("method ======> onSaveInstanceState")
    }

    /*
     *
     * @author weiggle
     * @date 2021/11/9 21:58
     * @param null
     * @return null
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        println("method ======> onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }


    /**
     *
     * @author weiggle
     * @date 2021/11/9 21:59
     * @param null
     * @return null
     */
    override fun onRetainCustomNonConfigurationInstance(): Any {
        println("method ======> onRetainCustomNonConfigurationInstance")
        return Any()
    }

    /**
     *
     * @author weiggle
     * @date 2021/11/9 21:59
     * @param null
     * @return null
     */
    override fun getLastNonConfigurationInstance(): Any? {
        println("method ======> getLastNonConfigurationInstance")
        return super.getLastNonConfigurationInstance()
    }

}