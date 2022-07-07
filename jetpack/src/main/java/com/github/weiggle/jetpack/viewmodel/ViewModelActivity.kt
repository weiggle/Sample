package com.github.weiggle.jetpack.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.github.weiggle.jetpack.R
import com.github.weiggle.jetpack.observer.CustomLifeOwner
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

class ViewModelActivity : AppCompatActivity() {

    lateinit var myViewModel: MyViewModel
    lateinit var mText: TextView
//    lateinit var nomalText: TextView
//    lateinit var mBtn: Button
//    var index = 0

    lateinit var cutsomOwner: CustomLifeOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        initLifeOwner()
        mText = findViewById(R.id.text)
//        nomalText = findViewById(R.id.normalText)
//        mBtn = findViewById(R.id.btn)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.myString.observe(this) {
            println("myString===========>${it}")
        }
        myViewModel.myString.observe(this, Observer {

        })

        findViewById<Button>(R.id.btn).setOnClickListener {   myViewModel.ss(3) }


        lifecycleScope.launch {
//            myViewModel.countDownFlow.collectLatest {
//                mText.text = "this is value ====> $it"
//            }

            myViewModel.stateFlow.collect {
                Toast.makeText(this@ViewModelActivity, "$it", Toast.LENGTH_SHORT).show()
                mText.text= "this is value ==> $it"
            }
        }


//        mBtn.setOnClickListener {
//            index++
//            myViewModel.setData("this is viewModel===>$index")
//            nomalText.text = "this is normal data===>$index"
//        }
//        initBtn(R.id.btn) {
////            cutsomOwner.setData("tdibciwbvciebvie")
//            myViewModel.incrementCounter()
//        }



        initBtn(R.id.btn2) {
            myViewModel.secondSource()
        }

        initBtn(R.id.btn3) {
            myViewModel.removeSource()
        }

        initBtn(R.id.btn4) {
            myViewModel.removeSecondSource()
        }

        initBtn(R.id.btn5) {
            myViewModel.testSource().observe(this) {
                println("source method===========>")
            }
            myViewModel.setData(Random(1000).toString())
        }

        initBtn(R.id.btn6) {
            myViewModel.setSecond(Random(1000).nextInt())
        }

    }

    fun initBtn(resId: Int, block: () -> Unit) {
        findViewById<Button>(resId).setOnClickListener { block.invoke() }
    }

    private fun initLifeOwner() {
        cutsomOwner = CustomLifeOwner()
        cutsomOwner.show()
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