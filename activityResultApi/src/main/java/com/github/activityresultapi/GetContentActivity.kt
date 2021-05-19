package com.github.activityresultapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.github.activityresultapi.databinding.ActivityGetContentBinding

class GetContentActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityGetContentBinding
    private var launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri ->
            viewBinding.textView.text = uri.toString()
            viewBinding.imageView.setImageURI(uri)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGetContentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.apply {
            getContent.setOnClickListener {
                launcher()
            }
        }
    }

    private fun launcher() {
        launcher.launch("image/*")
    }
}