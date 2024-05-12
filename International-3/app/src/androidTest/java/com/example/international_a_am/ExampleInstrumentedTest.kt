package com.example.international_a_am

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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
        assertEquals("com.example.international_a_am", appContext.packageName)
    }
    @Test
    fun AndroidTest() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(context.packageName)), 10000)
        device.wait(Until.hasObject(By.res("a")), 5000)

        device.findObject(By.res("a")).scrollUntil(Direction.DOWN, Until.findObject(By.res("1")))
      device.findObject(By.textContains("Tickets")).click()
        println("WHOFEJFIOIWEJFI(OWEIWEJIOWEJFIOEJ")
        device.wait(Until.findObject(By.res("b")), 2000)
      device.findObject(By.res("b")).click()
        device.wait(Until.findObject(By.text("Closing Ceremony")), 4000)
        device.findObject(By.textContains("Closing")).click()
        Thread.sleep(2000)
        device.findObject(By.textContains("Opening")).click()

        Thread.sleep(2000)
        device.click(100, 100)
        Thread.sleep(2000)
        device.findObject(By.textContains("Input")).click()
        device.findObject(By.res("input")).text = "Yrrrrres"

        Thread.sleep(2000)
        device.findObject(By.textContains("Choose")).click()
        Thread.sleep(1000)
        device.click(500, 2000)
        Thread.sleep(1000)
        device.findObject(By.res("create")).click()

        Thread.sleep(2000)
        device.findObject(By.text("Records")).click()
        Thread.sleep(2000)
        device.findObject(By.text("While using the app")).click()
        //device.sleep()
        Thread.sleep(2000)

//So using button opr text field or COlumn, use res
        //Dropdown button just use text or rtext contains
        //If it's an icon in a navigation icon use text or text contins
        //find the row in the list by using text contAINS OR TEXT
        //use iterative test tags



    }
}