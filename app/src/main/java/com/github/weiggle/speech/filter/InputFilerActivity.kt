package com.github.weiggle.speech.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText
import com.github.weiggle.R

class InputFilerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_filer)
        val editText = findViewById<EditText>(R.id.edittext)
        editText.filters = arrayOf(getFilter(10))
    }


    private fun getFilter(mMax: Int): InputFilter {
        return object : InputFilter {
            override fun filter(
                source: CharSequence,
                start: Int,
                end: Int,
                dest: Spanned?,
                dstart: Int,
                dend: Int
            ): CharSequence {

                println("source=========${source?.toString()}=====start==>${start}===>${end}====>${dest.toString()}===>${dstart}=====>${dend}")
                var keep = mMax - (dest!!.length - (dend - dstart))
                if (keep <= 0) {
                    return "";
                } else if (keep >= end - start) {
                    return source; // keep original
                } else {
                    keep += start
                    if (Character.isHighSurrogate(source!!.toString().get(keep - 1))) {
                        --keep;
                        if (keep == start) {
                            return "";
                        }
                    }
                    return source?.subSequence(start, keep) ?: ""
                }
            }
        }
    }
}