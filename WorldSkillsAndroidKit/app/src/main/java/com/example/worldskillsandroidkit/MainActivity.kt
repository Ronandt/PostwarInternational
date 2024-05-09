package com.example.worldskillsandroidkit

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipData
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.graphics.Bitmap
import android.graphics.drawable.Icon
//import android.health.connect.datatypes.SexualActivityRecord
import android.os.Build
import android.os.Bundle
import android.os.Environment
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

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
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
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.database.getStringOrNull
import androidx.core.graphics.drawable.IconCompat
import androidx.core.net.toUri
import androidx.exifinterface.media.ExifInterface

import coil.compose.AsyncImage
import com.example.worldskillsandroidkit.data.SocketManager
//import com.example.worldskillsandroidkit.presentation.DragAndDropHandler

import com.example.worldskillsandroidkit.presentation.ui.theme.WorldSkillsAndroidKitTheme
import com.example.worldskillsandroidkit.utils.SendBinaryIntents
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity :ComponentActivity(){
    @OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class,
        ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class
    )
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val pullRefreshState = rememberPullRefreshState(
                refreshing = false,
                onRefresh = {

                })
            val context = LocalContext.current
           // (context as Activity).window.attributes.screenBrightness = 1f
            var activityResult = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
                println(File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "tmp").totalSpace)

            }

            LaunchedEffect(key1 = Unit) {
                context.resources.getIdentifier("bruh", "drawable", context.packageName)
            }
            var infiniteAnimation = rememberInfiniteTransition()
            val thing by infiniteAnimation.animateFloat(
                initialValue = 0.5f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(tween(500, easing = LinearEasing), repeatMode = RepeatMode.Restart)
            )

            var composableBounds by remember {
                mutableStateOf<Rect?>(null)
            }
            LaunchedEffect(key1 = Unit) {
               // SocketManager().connect()
               //var file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "tmp")
                 //   activityResult.launch(FileProvider.getUriForFile(context,"${context.packageName}.provider",file))
               // println("4432243")
             //   println(ExifInterface(file)?.dateTime?.let { f(it) })

            }

        //   val context = LocalContext.current
            val view = LocalView.current
            Column(modifier = Modifier.pullRefresh(pullRefreshState)) {
                Text(text = "Whatever testihng")

                AsyncImage(model =  File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "tmp").toUri(), contentDescription = "Yes" )
                Column(modifier = Modifier.background(brush = Brush.linearGradient(listOf(Color.Green, Color.White)))) {
                    Button(onClick = {

                        val target = Intent(context, MainActivity::class.java)
                        val bubbleIntent = PendingIntent.getActivity(context, 0, target,
                            PendingIntent.FLAG_MUTABLE/* flags */)
                        val category = "com.example.category.IMG_SHARE_TARGET"

                        val chatPartner = android.app.Person.Builder()
                            .setName("Chat partner")
                            .setImportant(true).setName("flflflflfllflf")
                            .build()

// Create a sharing shortcut.
                        val shortcutId = System.currentTimeMillis().toString()
                        val shortcut =
                            ShortcutInfo.Builder(context, shortcutId)
                                .setCategories(setOf(category))
                                .setIntent(Intent(Intent.ACTION_DEFAULT))
                                .setLongLived(true)
                                .setShortLabel("Chat partner")
                                .build()

// Create a bubble metadata.
                        val bubbleData = Notification.BubbleMetadata.Builder(bubbleIntent,
                            Icon.createWithResource(context, R.drawable.ic_launcher_background))
                            .setDesiredHeight(1000).setIcon(Icon.createWithResource(context, R.drawable.ic_launcher_background))
                            .build()

// Create a notification, referencing the sharing shortcut.
                        val builder = Notification.Builder(context,"CHANNEL_ID")
                            .setContentIntent(bubbleIntent)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setBubbleMetadata(bubbleData)
                            .setShortcutId(shortcutId)
                            .addPerson(chatPartner)


                       val notificationManager =  context.getSystemService(NotificationManager::class.java)
                        notificationManager.createNotificationChannel(NotificationChannel("CHANNEL_ID", "Chnnel name",NotificationManager.IMPORTANCE_HIGH))
                        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
                    }) {
                        Text(text = "Bla bla bla bubbl;e test")
                    }
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    //DragAndDropHandler()
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

fun f(unixTime: Long) {
    val a = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    println(a.format(Date(unixTime)))
}

fun b(format: String) {
    val fr = SimpleDateFormat("dd/MM/yyyy",  Locale.ENGLISH)
    fr.parse("4")
}