package com.github.weiggle.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.github.weiggle.jetpack.utils.IntervalTask
import kotlinx.coroutines.*

class TestIntervalActivity : AppCompatActivity() {

    private var scope: CoroutineScope? = null

    private var count = 0
    private lateinit var textView: TextView
    private lateinit var starButton: Button
    private lateinit var endButton: Button
    lateinit var intervalTask: IntervalTask<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_interval)

        textView = findViewById(R.id.text)
        starButton = findViewById(R.id.start)
        endButton = findViewById(R.id.end)

        intervalTask = IntervalTask(this)
        starButton.setOnClickListener {
            intervalTask.interval(2000) {
                count++
                lifecycleScope.launch(Dispatchers.Main) {
                    textView.text = "this is creste by" + count
                }
            }
        }

        endButton.setOnClickListener {
            intervalTask.cancel()
        }
    }


}
