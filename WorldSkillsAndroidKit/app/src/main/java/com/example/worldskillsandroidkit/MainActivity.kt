package com.example.worldskillsandroidkit

import android.app.Activity
import android.app.PendingIntent
import android.content.ClipData
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.health.connect.datatypes.SexualActivityRecord
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.view.HapticFeedbackConstants
import android.view.PixelCopy
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.Person
import androidx.core.content.ContextCompat
import androidx.core.database.getStringOrNull
import androidx.core.graphics.drawable.IconCompat
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import com.example.worldskillsandroidkit.Practice.Db
import com.example.worldskillsandroidkit.Practice.Test
import com.example.worldskillsandroidkit.data.RealmDatabase
import com.example.worldskillsandroidkit.presentation.DragAndDropHandler

import com.example.worldskillsandroidkit.presentation.ui.theme.WorldSkillsAndroidKitTheme
import com.example.worldskillsandroidkit.utils.SendBinaryIntents
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : FragmentActivity() {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            val db = remember { Db().getDb()}
            val scope = rememberCoroutineScope()
            val testData =  db.query<Test>().find().asFlow().map { it.list }.collectAsState(initial = listOf<Test>())
            LaunchedEffect(Unit) {

               withContext(Dispatchers.IO) {
                   db.write {

                       copyToRealm(Test())
                   }


               }
            }

            var composableBounds by remember {
                mutableStateOf<Rect?>(null)
            }

        //   val context = LocalContext.current
            val view = LocalView.current
            Column {
               /* Column(modifier = Modifier.fillMaxSize()) {
                    DragAndDropHandler()
                }*/
                LazyColumn {
                    items(testData.value.size) {
                        Text(testData.value[it].data, modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                scope.launch(Dispatchers.IO) {
                                    db.write {
                                        val query = this.query<Test>("id == $0", testData.value[it].id)
                                        delete(query)
                                    }

                                }
                            })

                    }
                }
                Column {
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
                        //  Notification(applicationContext).notify("Bruh")
                        scope.launch {
                            println("?")
                            SendBinaryIntents(context).sendBinary("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1280px-Image_created_with_a_mobile_phone.png")
                        }
                    }) {
                        Text(text = "Test")
                    }
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
            }

             /*   Column(modifier = Modifier
                    .background(Color.Black)
                    .size(100.dp, 200.dp)
                    .onGloballyPositioned {
                        composableBounds = it.boundsInWindow()
                    }) {
                    Text(text = "This is the target")
                }*/

      


           /* val launchActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
                if(it!=null) {
                    context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }


            }*/

              /*  Column(modifier = Modifier
                    .fillMaxSize()
                    .semantics { this.testTagsAsResourceId = true }) {
                  androidx.compose.foundation.lazy.LazyColumn(modifier = Modifier.testTag("test")) {
                        items(100) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(text = (0..100).random().toString())
                            }

                        }
                    }
                }*/
            WorldSkillsAndroidKitTheme {
                // A surface container using the 'background' color from the theme
           //     val haptic2 = LocalView.current






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
}}

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