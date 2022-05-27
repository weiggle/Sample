package com.github.weiggle.jetpack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.weiggle.jetpack.databinding.ActivityMainBinding
import com.github.weiggle.jetpack.fragmentresult.FragmentA
import com.github.weiggle.jetpack.observer.viewmodel.CustomViewModel
import com.github.weiggle.jetpack.viewmodel.ViewModelActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CustomViewModel
    lateinit var viewBinding: ActivityMainBinding

    private val list = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        initData()
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerView.adapter = MyAdapter(this, list) { item, position ->

            val clazz = when (position) {
                0 -> ViewModelActivity::class.java
                1 -> TestIntervalActivity::class.java
                else -> ViewModelActivity::class.java
            }
            val intent = Intent(this, clazz)
            startActivity(intent)
        }


        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                println("this is repeatOnLifecycle RESUMED")
            }

            lifecycle.whenResumed {
                println("this is whenResumed RESUMED")
            }
        }

        lifecycleScope.launchWhenResumed {
            println("this is launchWhenResumed RESUMED")

        }
    }

    fun initData() {
        list.add("ViewModelActivity")
        list.add("TestIntervalActivity")
    }
}

class MyAdapter(
    val context: Context,
    val data: List<String>,
    val onItemClick: (String, Int) -> Unit,
) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.list_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setUp(data[position])
        holder.view.setOnClickListener {
            onItemClick.invoke(data[position], position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    fun setUp(item: String) {
        view.findViewById<TextView>(R.id.text).text = item
    }
}