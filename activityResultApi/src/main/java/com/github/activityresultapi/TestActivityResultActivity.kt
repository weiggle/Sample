package com.github.activityresultapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.activityresultapi.databinding.ActivityMainBinding
import com.github.activityresultapi.databinding.ActivityTestResultBinding

class TestActivityResultActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTestResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTestResultBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.apply {
            testIntent.postDelayed({
                testIntent.setText(intent.getStringExtra("data"))
            }, 2000)
            testOK.setOnClickListener {
                setResult(RESULT_OK, Intent().apply {
                    putExtra("data", "this is from TestActivityResultActivity")
                })
                finish()
            }
        }
    }
}