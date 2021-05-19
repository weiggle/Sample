package com.github.activityresultapi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.github.activityresultapi.databinding.ActivityCustomContractBinding

class CustomActivityContract : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCustomContractBinding
    private val launcher = registerForActivityResult(PickRingtone()) { uri ->

        uri?.let {
            viewBinding.textView.text = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCustomContractBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.selectImage.setOnClickListener {
            launcher.launch(RingtoneManager.TYPE_RINGTONE)
        }
    }
}

class PickRingtone : ActivityResultContract<Int, Uri?>() {
    override fun createIntent(context: Context, ringtoneType: Int): Intent {
        return Intent(RingtoneManager.ACTION_RINGTONE_PICKER).apply {
            putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, ringtoneType)
//            putExtra(
//                RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT,
//                false
//            )
//            putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置来电铃声11")

        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }
        return intent?.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
    }
}

