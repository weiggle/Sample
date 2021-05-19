package com.github.activityresultapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.activityresultapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    fun startActity(view: View) {
        startActivity(Intent(this, StartActivity::class.java))
    }

    fun requestPermission(view: View) {
        startActivity(Intent(this, RequestPermissionActivity::class.java))
    }

    fun requestMultiPermission(view: View) {
        startActivity(Intent(this, RequestMultiPermissionActivity::class.java))
    }

    fun takePicture(view: View) {
        startActivity(Intent(this, TakePictureActivity::class.java))
    }

    fun takePicturePreview(view: View) {
        startActivity(Intent(this, TakePicturePreviewActivity::class.java))
    }

    fun pickContact(view: View) {
        startActivity(Intent(this, PickContactActivity::class.java))
    }
}