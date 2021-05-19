package com.github.activityresultapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.github.activityresultapi.databinding.ActivityTakePicturePreviewBinding

class TakePicturePreviewActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTakePicturePreviewBinding
    private val launcher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        if (null != it) {
            Toast.makeText(this, "拍照成功",Toast.LENGTH_SHORT).show()
            viewBinding.takePictureImage.setImageBitmap(it)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTakePicturePreviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.apply {
            takePicturePreview.setOnClickListener {
                launcher()
            }
        }
    }

    private fun launcher() {
        launcher.launch(null)
    }
}