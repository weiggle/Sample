package com.github.weiggle.drawable

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val xmlView = findViewById<ImageView>(R.id.xml)
        xmlView.setBackgroundResource(R.drawable.live_play)
        val background = xmlView.background as? AnimationDrawable
        background?.start()
        xmlView.setOnClickListener {
            xmlView.setBackgroundResource(R.drawable.live_play)
        }


//        val animationDrawable = AnimationDrawable().apply {
//            setItem(R.drawable.living_000)
//            setItem(R.drawable.living_001)
//            setItem(R.drawable.living_002)
//            setItem(R.drawable.living_003)
//            setItem(R.drawable.living_004)
//            setItem(R.drawable.living_005)
//            setItem(R.drawable.living_006)
//            setItem(R.drawable.living_007)
//            setItem(R.drawable.living_008)
//            setItem(R.drawable.living_009)
//            setItem(R.drawable.living_010)
//            setItem(R.drawable.living_011)
//            setItem(R.drawable.living_012)
//            setItem(R.drawable.living_013)
//            setItem(R.drawable.living_014)
//            setItem(R.drawable.living_015)
//        }
//
//        val contrs = findViewById<ImageView>(R.id.constr)
//        contrs.setImageDrawable(animationDrawable)
//        animationDrawable.start()


        val text = object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            }
        }
    }

    private fun AnimationDrawable.setItem(resId: Int, duration: Int = 37) {
        val drawable = ContextCompat.getDrawable(this@MainActivity, resId)
        addFrame(drawable!!, duration)
    }
}