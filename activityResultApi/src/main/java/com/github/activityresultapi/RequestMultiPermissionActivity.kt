package com.github.activityresultapi

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.github.activityresultapi.databinding.ActivityRequestMultiPermissionBinding

class RequestMultiPermissionActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityRequestMultiPermissionBinding

    private var launcher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            val sb = StringBuilder("权限结果：\n")
            it.entries.forEach {
                sb.append(it.key)
                    .append(":")
                    .append(if (it.value) "有权限" else "无权限")
                    .append("\n\n")
            }
            viewBinding.resultText.setText(sb)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRequestMultiPermissionBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.apply {
            requestMultiPermission.setOnClickListener {
                launch()
            }
        }
    }

    private fun launch() {
        launcher.launch(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
    }
}