package com.github.weiggle.speech

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingDeque

/**
 * @author wei.li
 * @created on 2021/10/21
 * @desc desc
 *
 */
class TextSpeech : DefaultLifecycleObserver {

    lateinit var mTextSpeech: TextToSpeech
    var queue: ArrayBlockingQueue<String>  = ArrayBlockingQueue(1000)
    private var contextWeakReference: WeakReference<Context>? = null
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        Thread{
            repeat(3) {
                queue.put("这是我的语音五倍被我UI热UI而不脆而不不如陪吧是恶如biu无人不不热IBUUI而U币"+it.times(2))
            }
        }.start()

    }

    override fun onStop(owner: LifecycleOwner) {
        mTextSpeech.stop()
        mTextSpeech.shutdown()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun speechText() {
        val take = queue.take()
        if (TextUtils.isEmpty(take)){
            contextWeakReference?.get()?.let {
                Toast.makeText(it, "请输入要朗读的文字", Toast.LENGTH_SHORT).show()
            }
            return
        }




        mTextSpeech.speak(take, TextToSpeech.QUEUE_FLUSH, null, take.hashCode().toString())
//        mTextSpeech.speak(text+"222", TextToSpeech.QUEUE_FLUSH, null)
//        mTextSpeech.speak(text+"333", TextToSpeech.QUEUE_FLUSH, null)
        mTextSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener(){
            override fun onStart(utteranceId: String?) {
                println("--------------------onStart")
            }

            override fun onDone(utteranceId: String?) {
                println("--------------------onDone")
                speechText()
            }

            override fun onError(utteranceId: String?) {
                println("--------------------onError")
            }
        })
    }

    fun addNewSpeech()  {
        Thread{
            repeat(3) {
                queue.put("这是我的语音五倍被我UI"+it.times(2))
            }
        }.start()
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
        }, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID )
        mTextSpeech.setPitch(0f)
        mTextSpeech.setSpeechRate(0.8f)
    }
}
