package com.example.worldskillsandroidkit

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnit4
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
    private lateinit var device: UiDevice
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.worldskillsandroidkit", appContext.packageName)
    }

    @Test
    fun uiAutomatorTest() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
         val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        context.startActivity(context.packageManager.getLaunchIntentForPackage(context.packageName))
        device.wait(Until.hasObject(By.pkg(context.packageName)), 10000)


    /*    device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        context.startActivity(context.packageManager.getLauchIntentForPackage(context.packageName))*

        device = UiDevice.getInstane(IntrumentationRegistyr.getInstrumentation())
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        context.startAcitvity(context.pacckageManger.getInstrumentatFOrPckage(cnotext.pckagename))
        /
     */


        device.findObject(By.res("test")).scrollUntil(Direction.DOWN, Until.findObject(By.textContains("30")))
        device.findObject(By.desc("close")).click()
        Thread.sleep(4000)


        device.findObject(By.res("search")).click()
        Thread.sleep(1000)
        device.findObject(By.res("title")).text = "web"


            //manual scroll
        val screenWidth = device.displayWidth
        val screenHeight = device.displayHeight
        val startX = screenWidth / 2 // Swipe horizontally at the center of the screen
        val startY = screenHeight / 4 // Start swiping from one quarter down the screen
        val endY = screenHeight * 3 / 4 // Swipe to three quarters down the screen
        device.swipe(startX, endY, startX, startY, 10)

    }


}