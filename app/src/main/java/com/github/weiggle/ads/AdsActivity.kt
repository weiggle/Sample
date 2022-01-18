package com.github.weiggle.ads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.github.weiggle.R
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailabilityLight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ads)
        val googlePlayServicesAvailable =
            GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(this)
        if (googlePlayServicesAvailable == ConnectionResult.SUCCESS) {
            val launchWhenStarted = lifecycleScope.launch(Dispatchers.IO) {
                val advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this@AdsActivity)
                println("this is available======>$advertisingIdInfo====")
                withContext(Dispatchers.Main){
                    findViewById<TextView>(R.id.ads).text = advertisingIdInfo.id
                }
            }
        }
    }
}