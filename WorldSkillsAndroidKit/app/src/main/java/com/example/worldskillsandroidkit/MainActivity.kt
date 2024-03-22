package com.example.worldskillsandroidkit

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.view.HapticFeedbackConstants
import android.view.PixelCopy
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.database.getStringOrNull
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import com.example.worldskillsandroidkit.Practice.API
import com.example.worldskillsandroidkit.Practice.Notification
import com.example.worldskillsandroidkit.presentation.SendBinaryIntents
import com.example.worldskillsandroidkit.presentation.ui.theme.WorldSkillsAndroidKitTheme
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LaunchedEffect(key1 = Unit) {
               println( API().Thing())
                var layout = window.attributes

                layout.screenBrightness = 1f
             //   win
            }
            var composableBounds by remember {
                mutableStateOf<Rect?>(null)
            }
            val context = LocalContext.current
            val view = LocalView.current
            Column {
                Button(onClick = {
                    var bitmap = composableBounds?.height?.toInt()
                        ?.let { composableBounds?.width?.toInt()
                            ?.let { it1 -> Bitmap.createBitmap(it1, it, Bitmap.Config.ARGB_8888) } }
                    if (bitmap != null) {
                        PixelCopy.request((context as Activity).window,composableBounds?.toAndroidRect(), bitmap, {}, Handler(
                            Looper.getMainLooper()))
                        MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "", "")
                    }

                }) {
                    Text(text = "Download image")

                }
             /*   Column(modifier = Modifier
                    .background(Color.Black)
                    .size(100.dp, 200.dp)
                    .onGloballyPositioned {
                        composableBounds = it.boundsInWindow()
                    }) {
                    Text(text = "This is the target")
                }*/

                Column {
                    Box(modifier = Modifier.size(100.dp).background(Color.Black).dragAndDropSource(drawDragDecoration = {

                    }) {
                        startTransfer(DragAndDropTransferData(
                        clipData = ClipData.newIntent("Food item", Intent()),
                        ))
                    }) {

                    }
                }
            }


           /* val launchActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
                if(it!=null) {
                    context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

            }*/

            WorldSkillsAndroidKitTheme {
                // A surface container using the 'background' color from the theme
           //     val haptic2 = LocalView.current
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                var launcherActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {


                }
//can't test haptic feedback generator

LaunchedEffect(key1 = Unit) {
    launcherActivity.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    //launchActivity.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}

                    Button(onClick = {

                    //haptic2.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                        Notification(applicationContext).notify("Bruh")
                        scope.launch {

                            SendBinaryIntents(context).sendBinary("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1280px-Image_created_with_a_mobile_phone.png")
                        }
                    }) {
                        Text(text = "Test")
                    }





            /*    ModalNavigationDrawer(drawerContent = {   ModalDrawerSheet(
                    drawerShape = MaterialTheme.shapes.small,
                    drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    drawerContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    drawerTonalElevation = 4.dp,
                ) {
                    Text(text = "HI")
                }}) {}*/
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorldSkillsAndroidKitTheme {
        Greeting("Android")
    }
}