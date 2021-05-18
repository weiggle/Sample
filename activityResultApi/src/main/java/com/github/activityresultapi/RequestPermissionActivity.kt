package com.github.activityresultapi

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.github.activityresultapi.databinding.ActivityRequestPermissionBinding

class RequestPermissionActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityRequestPermissionBinding
    private val permessionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(this, "我目前${if (it) "已有" else "还没有"}写权限", Toast.LENGTH_SHORT).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRequestPermissionBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.apply {
            requestPermission.setOnClickListener {
                permessionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }
}