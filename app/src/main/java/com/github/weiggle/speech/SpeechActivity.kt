package com.github.weiggle.speech

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.weiggle.R

class SpeechActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech)

        checkPermission()
        val textSpeech = TextSpeech()
        lifecycle.addObserver(textSpeech)
        textSpeech.initTextToSpeech(this)
        val editText = findViewById<EditText>(R.id.edit)
        findViewById<Button>(R.id.btn).setOnClickListener {
            textSpeech.speechText()
        }


         findViewById<Button>(R.id.add).setOnClickListener {
            textSpeech.addNewSpeech()
        }

    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
         Toast.makeText(this, "已经拥有权限", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 100)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}