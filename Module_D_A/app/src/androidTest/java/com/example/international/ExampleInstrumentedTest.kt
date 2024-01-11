package com.example.international

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
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

        device.findObject(By.text("Information and Communication Technology")).click()
        device.findObject(By.text("Information Network Cabling")).click()
        device.findObject(By.res("search")).click()
        device.findObject(By.res("title")).text = "web"
        device.findObject(By.res("skill")).text = "Mobile"
        device.findObject(By.res("start")).text = "03/21/2022"
        device.findObject(By.res("end")).text = "09/30/2022"
        device.findObject(By.res("searchIcon")).click()

        Thread.sleep(4000)
    }
}