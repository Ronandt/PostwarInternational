package com.example.pocketvault

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import kotlinx.coroutines.delay

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
        assertEquals("com.example.pocketvault", appContext.packageName)
    }

    @Test
    fun runAutomatedTest() {
        val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val launchApplicationIntent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        context.startActivity(launchApplicationIntent)
        uiDevice.wait(Until.hasObject(By.pkg(context.packageName)), 10000)
        uiDevice.findObject(By.text("Register")).click()
        uiDevice.wait(Until.findObject(By.res("registerUsername")), 3000)
        uiDevice.findObject(By.res("registerUsername")).text = "test"
        uiDevice.findObject(By.res("registerPassword")).text = "test"
        uiDevice.findObject(By.res("registerConfirmPassword")).text = "test"
        uiDevice.findObject(By.text("Register")).click()
        uiDevice.wait(Until.findObject(By.res("loginUsername")), 3000)
        Thread.sleep(1000)
        uiDevice.findObject(By.res("loginUsername")).text = "test"
        Thread.sleep(1000)
        uiDevice.findObject(By.res("loginPassword")).text = "test"

        uiDevice.findObject(By.text("Login")).click()

        uiDevice.wait(Until.findObject(By.text("Settings")), 10000)
        Thread.sleep(1000)
        uiDevice.findObject(By.text("Settings")).click()
        uiDevice.wait(Until.findObject(By.text("Passwords")), 10000)
        uiDevice.findObject(By.text("Passwords")).click()
        uiDevice.wait(Until.findObject(By.text("Gmail")), 10000)
        uiDevice.findObject(By.text("Gmail")).click()
       uiDevice.wait(Until.findObject(By.text("Edit")), 10000)
        uiDevice.findObject(By.text("Edit")).click()
        uiDevice.wait(Until.findObject(By.res("editTitle")), 10000)
        uiDevice.findObject(By.res("editTitle")).text = "Edited"
        uiDevice.findObject(By.res("editUsername")).text = "Edited"
        uiDevice.findObject(By.res("editPassword")).text = "Edited"
        uiDevice.findObject(By.res("editWebsite")).text = "Edited"
        uiDevice.findObject(By.text("Save")).click()
        uiDevice.wait(Until.findObject(By.text("Edited")), 10000)
        Thread.sleep(2000)

        uiDevice.findObject(By.res("search")).text = "Edited"
        Thread.sleep(1000)

        uiDevice.wait(Until.findObject(By.text("Edited")), 10000)
        uiDevice.findObject(By.text("Edited")).click()
        uiDevice.wait(Until.findObject(By.text("Delete")), 10000)
        uiDevice.findObject(By.text("Delete")).click()
        uiDevice.wait(Until.findObject(By.text("Yes")), 10000)
        uiDevice.findObject(By.text("Yes")).click()
        uiDevice.wait(Until.findObject(By.desc("Add")), 10000)
        uiDevice.findObject(By.desc("Add")).click()
        Thread.sleep(1000)
        uiDevice.wait(Until.findObject(By.res("createTitle")), 10000)
        uiDevice.findObject(By.res("createTitle")).text = "Added"
        uiDevice.findObject(By.res("createUsername")).text = "Added"
        uiDevice.findObject(By.res("createPassword")).text = "Added"
        uiDevice.findObject(By.res("createWebsite")).text  = "Added"
        uiDevice.findObject(By.text("Save")).click()
        uiDevice.wait(Until.findObject(By.text("Settings")), 10000)
        Thread.sleep(1000)
        uiDevice.findObject(By.text("Settings")).click()
        uiDevice.wait(Until.findObject(By.text("Export data to JSON")), 10000)
        uiDevice.findObject(By.text("Export data to JSON")).click()
        uiDevice.findObject(By.text("Import backup data")).click()
        Thread.sleep(5000)
        uiDevice.wait(Until.findObject(By.textContains("AM")), 10000)
        Thread.sleep(2000)
        uiDevice.findObject(By.textContains("AM")).click()
        Thread.sleep(2000)
        uiDevice.wait(Until.findObject(By.text("Passwords")), 10000)
        uiDevice.findObject(By.text("Passwords")).click()
        Thread.sleep(3000)
        uiDevice.wait(Until.findObject(By.text("Settings")), 10000)
        uiDevice.findObject(By.text("Settings")).click()
        uiDevice.wait(Until.findObject(By.text("Log out")), 10000)
        uiDevice.findObject(By.text("Log out")).click()
        Thread.sleep(10000)



            //uiDevice.findObject(By.res())








    }
}