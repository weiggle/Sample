package com.github.weiggle.json

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.weiggle.json.parser.JSONParser
import com.github.weiggle.json.parser.model.JsonObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.lang.Exception

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.github.weiggle.json", appContext.packageName)
    }


    @Test
    @Throws(Exception::class)
    fun beautifyJSON() {
        val json =
            "{\"name\": \"狄仁杰\", \"type\": \"射手\", \"ability\":[\"六令追凶\",\"逃脱\",\"王朝密令\"],\"history\":{\"DOB\":630,\"DOD\":700,\"position\":\"宰相\",\"dynasty\":\"唐朝\"}}"
        println("原 JSON 字符串：")
        println(json)
        println("\n")
        println("美化后的 JSON 字符串：")
        val jsonParser = JSONParser()
        val drj: JsonObject = jsonParser.fromJSON(json) as JsonObject
        System.out.println(drj)
    }
}