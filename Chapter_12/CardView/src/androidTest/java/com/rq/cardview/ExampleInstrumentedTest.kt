package com.rq.cardview

import android.provider.ContactsContract
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

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
        assertEquals("com.rq.floatingactionbutton", appContext.packageName)
    }

    @Test
    fun main(){
        val time =1628063282673
        val format: SimpleDateFormat = SimpleDateFormat("yyyy年MM月dd日-hh时mm分ss秒")
        val date = Date(time)
        println("time: " + format.format(date))
    }

}