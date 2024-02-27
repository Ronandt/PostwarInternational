package com.example.international

import android.content.Intent
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
import org.junit.runner.manipulation.Ordering.Context

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
        assertEquals("com.example.international", appContext.packageName)
    }

    @Test
    fun uiAutomatorTest() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        val launcher: String = device.launcherPackageName
        device.wait(
            Until.hasObject(By.pkg(launcher).depth(0)),
            10000
        )
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = context.packageManager.getLaunchIntentForPackage("com.example.international")
            ?.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg("com.example.international")), 10000)
        device.findObject(By.res("menu")).click()

        Thread.sleep(1000)
        device.findObject(By.text("Information and Communication Technology")).click()
        Thread.sleep(1000)
        device.findObject(By.text("Information Network Cabling")).click()
        Thread.sleep(1000)
        device.findObject(By.res("skillsImage")).click()
        Thread.sleep(1000)

        val screenWidth = device.displayWidth
        val screenHeight = device.displayHeight
        val startX = screenWidth / 2 // Swipe horizontally at the center of the screen
        val startY = screenHeight / 4 // Start swiping from one quarter down the screen
        val endY = screenHeight * 3 / 4 // Swipe to three quarters down the screen
        device.swipe(startX, endY, startX, startY, 10)

        Thread.sleep(1000)
        device.click(100, 100 )

        Thread.sleep(1000)
        device.findObject(By.res("search")).click()
        Thread.sleep(1000)
        device.findObject(By.res("title")).text = "web"
        Thread.sleep(1000)

        device.findObject(By.res("skill")).text = "Mobile"
        Thread.sleep(1000)
        device.findObject(By.res("start")).text = "21/03/2022"
        Thread.sleep(1000)
        device.findObject(By.res("end")).text = "30/09/2022"
        Thread.sleep(1000)
        device.findObject(By.res("searchIcon")).click()
        Thread.sleep(1000)
        device.findObject(By.res("close")).click()
        Thread.sleep(1000)
        device.findObject(By.res("skillsImage")).click()
        Thread.sleep(1000)
        device.swipe(startX, endY, startX, startY, 3)
        Thread.sleep(5000)
        device.click(346, 1200)
        Thread.sleep(5000)
        device.click(714, 1580)
        device.findObject(By.desc("Plus")).click()
        Thread.sleep(2000)
        device.findObject(By.desc("close")).click()
        Thread.sleep(4000)
    }
}