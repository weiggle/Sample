package com.github.weiggle

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.weiggle.ads.AdsActivity
import com.github.weiggle.drawable.shapeDrawable
import com.github.weiggle.mnn.MnnActivity
import com.github.weiggle.pools.ObjectPoolsActivity
import com.github.weiggle.speech.SpeechActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private val data = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: MyAdapter by lazy {
            MyAdapter {
                var clazz = when (it) {
                    0 -> SpeechActivity::class.java
                    1 -> MnnActivity::class.java
                    2 -> AdsActivity::class.java
                    3 -> ObjectPoolsActivity::class.java
                    else -> SpeechActivity::class.java
                }

                startActivity(Intent(this, clazz))
            }
        }
        initData()
        adapter.data = data
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        lifecycleScope.launchWhenCreated {
            println("lifecycleScope==============>launchWhenCreated")
        }
        lifecycleScope.launchWhenResumed {
            println("lifecycleScope==============>launchWhenResumed")
        }
        lifecycleScope.launchWhenStarted {
            println("lifecycleScope==============>launchWhenStarted")
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                println("lifecycleScope======================>this is resume")
            }
        }
    }

    private fun initData() {
        data.add("SpeechActivity")
        data.add("MnnActivity")
        data.add("AdsActivity")
        data.add("ObjectPoolsActivity")
    }


    class MyAdapter(val itemClick: (Int) -> Unit) : RecyclerView.Adapter<MyViewHolder>() {

        lateinit var data: List<String>


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.setText(data[position])
            holder.itemView.setOnClickListener {
                itemClick.invoke(position)
            }
        }

        override fun getItemCount() = data.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView = itemView.findViewById(R.id.textView)

    }
}