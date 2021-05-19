package com.github.activityresultapi

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.github.activityresultapi.databinding.ActivityTakePictureBinding
import java.io.File
import java.io.IOException

class TakePictureActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTakePictureBinding
    private var uri: Uri? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        Toast.makeText(this, if (it) "已存储" else "存储失败", Toast.LENGTH_SHORT).show()
//        if (it) {
//            uri?.let {
//                viewBinding.imagePreview.setImageURI(it)
//            }
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTakePictureBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.apply {
            takePicture.setOnClickListener {
                launcer()
            }
        }
    }

    private fun launcer() {
        val file = File(cacheDir, "picture.jpeg")
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (e: IOException) {
                e.printStackTrace();
            }
        }
        println("takePicture-------" + file.absolutePath)
        uri =
            FileProvider.getUriForFile(this, applicationContext.packageName + ".fileprovider", file)
        println("takePicture uri-------" + uri!!.path)
        launcher.launch(uri)
    }
}