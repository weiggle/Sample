package com.github.activityresultapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import com.github.activityresultapi.databinding.ActivityPickContactBinding


class PickContactActivity : AppCompatActivity() {
    private val launcher = registerForActivityResult(ActivityResultContracts.PickContact()) {
        if (null != it) {
            viewBinding.textView.setText(it.toString())
        }
    }

    private lateinit var viewBinding: ActivityPickContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPickContactBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.apply {
            pickContact.setOnClickListener {
                launcher()
            }
        }
    }

    private fun launcher() {
        launcher.launch(null)
    }
}