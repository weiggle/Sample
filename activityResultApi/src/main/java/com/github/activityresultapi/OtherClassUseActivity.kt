package com.github.activityresultapi

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.github.activityresultapi.databinding.ActivityOtherClassUseBinding

class OtherClassUseActivity : AppCompatActivity() {
    private lateinit var observer: MyLifecycleObserver
    private lateinit var viewBinding: ActivityOtherClassUseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOtherClassUseBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        observer = MyLifecycleObserver(this.activityResultRegistry)
        lifecycle.addObserver(observer)
        observer.getLiveData().observe(this) {
            viewBinding.imageView.setImageURI(it)
        }
        viewBinding.selectImage.setOnClickListener {
            observer.selectImage()
        }
    }
}

class MyLifecycleObserver(private val registry : ActivityResultRegistry): DefaultLifecycleObserver {

    private lateinit var launcher: ActivityResultLauncher<String>
    private val _liveData = MutableLiveData<Uri>()
    private val liveData = _liveData

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        launcher = registry.register("custom", owner, ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                _liveData.value = it
            }
        }
    }

    fun getLiveData() = liveData

    fun selectImage() {
        launcher.launch("image/*")
    }
}