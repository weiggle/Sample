package com.github.weiggle.speech

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.lang.ref.WeakReference
import java.util.*

/**
 * @author wei.li
 * @created on 2021/10/21
 * @desc desc
 *
 */
class TextSpeech : DefaultLifecycleObserver {

    lateinit var mTextSpeech: TextToSpeech
    private var contextWeakReference: WeakReference<Context>? = null
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

    }

    override fun onStop(owner: LifecycleOwner) {
        mTextSpeech.stop()
        mTextSpeech.shutdown()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

    fun speechText(text: String) {
        if (TextUtils.isEmpty(text)){
            contextWeakReference?.get()?.let {
                Toast.makeText(it, "请输入要朗读的文字", Toast.LENGTH_SHORT).show()
            }
            return
        }

        mTextSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        mTextSpeech.speak(text+"111", TextToSpeech.QUEUE_FLUSH, null)
        mTextSpeech.speak(text+"222", TextToSpeech.QUEUE_FLUSH, null)
        mTextSpeech.speak(text+"333", TextToSpeech.QUEUE_FLUSH, null)
        mTextSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener(){
            override fun onStart(utteranceId: String?) {
            }

            override fun onDone(utteranceId: String?) {
            }

            override fun onError(utteranceId: String?) {
            }
        })
    }

    fun initTextToSpeech(context: Context) {
        contextWeakReference = WeakReference(context)
        mTextSpeech = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(status: Int) {
                if (TextToSpeech.SUCCESS == status) {
                    val language = mTextSpeech.setLanguage(Locale.CHINA)
                    if (TextToSpeech.LANG_MISSING_DATA == language
                        || TextToSpeech.LANG_NOT_SUPPORTED == language
                    ) {
                        contextWeakReference!!.get()?.let {
                            Toast.makeText(it, "数据丢失或不支持", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        })
        mTextSpeech.setPitch(0.2f)
        mTextSpeech.setSpeechRate(0.8f)
    }
}
