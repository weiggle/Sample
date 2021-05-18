package com.github.activityresultapi

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.activityresultapi.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    val startActity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            viewBinding.testIntent.postDelayed({
                viewBinding.testIntent.setText(it.data!!.getStringExtra("data"))
            }, 2000)
        }
    }
    private lateinit var viewBinding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.apply {
            testOK.setOnClickListener {
                startActity.launch(
                    Intent(
                        this@StartActivity,
                        TestActivityResultActivity::class.java
                    ).apply {
                        putExtra("data", "this is from StartActivity")
                    })
            }
        }
    }
}