package com.github.weiggle.mnn

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.workbench.EasyMNN
import com.github.weiggle.R
import java.net.URI

class MnnActivity : AppCompatActivity() {

    lateinit var eidt: EditText
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mnn)

        EasyMNN.init(this)
        eidt = findViewById(R.id.edittext)
        btn = findViewById(R.id.btn)
        val sp = getPreferences(0)
        var url = sp.getString("url", null)
        if (!TextUtils.isEmpty(url)) {
            eidt.setText(url)
        }
        btn.setOnClickListener {
            val text = eidt.text
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(this, "URL 为空", Toast.LENGTH_SHORT).show()
            } else {
                //step 2、连接工作台
                url = text.toString()
                val uri: URI = URI.create(url)
                Thread{
                    EasyMNN.connectToWorkBench(uri)

                }.start()
                sp.edit().putString("url", url).apply()
            }
        }
    }
}