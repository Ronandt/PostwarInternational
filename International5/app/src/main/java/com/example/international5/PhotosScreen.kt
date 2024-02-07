package com.example.international5

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosScreen(navController: NavController) {

    var currentPage by remember {mutableStateOf(0)}
    val context = LocalContext.current
    var loadPagination by remember {mutableStateOf(0)}
    val photos = remember {
        mutableStateListOf<PhotoItem>()
    }
    LaunchedEffect(Unit) {
        WorldSkillsAPI().getPhotos()?.let { photos.addAll(it) }
        delay(500)
        loadPagination = 1

    }
    val scope = rememberCoroutineScope()

    BoxWithConstraints {
        if(maxWidth > maxHeight) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(26.dp), verticalArrangement = Arrangement.spacedBy(26.dp), modifier = Modifier.padding(20.dp)) {
                    var first = currentPage * 6
                    var second = (currentPage + 1) * 6
                    items(6) {
                        var dropdown by remember {mutableStateOf(false)}

                        if(photos?.getOrNull((currentPage * 6) + it)?.votes != null) {
                            var dialog by remember {mutableStateOf(false)}
                        Box(modifier = Modifier
                            .size(280.dp)
                            .combinedClickable(
                                onClick = { dialog = true },
                                onLongClick = {
                                    dropdown = true
                                    println("WHY")
                                }
                            )
                           ) {

                           Image(
                                LoaderImage(photos[it + (currentPage * 6)].imageLink), modifier = Modifier
                                    .matchParentSize().clip(RoundedCornerShape(10.dp))
                                  , contentDescription = "", contentScale = ContentScale.Crop)
                            Column(modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(Color(0xAA000000))) {
                                Text(text = "Votes " + photos?.get(maxOf(0, (currentPage-1)) + it)?.votes, color=  Color.White)
                                Text(text = "Popularity " + photos?.get(maxOf(0, (currentPage-1))+ it)?.popularity, color = Color.White)
                            }
                            DropdownMenu(expanded = dropdown, onDismissRequest = { dropdown = false }, offset = DpOffset(0.dp, -60.dp)) {
                                DropdownMenuItem(text = { Text("Share")}, onClick = {
                                    if (Build.VERSION.SDK_INT >= 24) {
                                        try {
                                            val m =
                                                StrictMode::class.java.getMethod("disableDeathOnFileUriExposure")
                                            m.invoke(null)
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }
                                    scope.launch {
                                        if (Build.VERSION.SDK_INT >= 24) {
                                            try {
                                                val m =
                                                    StrictMode::class.java.getMethod("disableDeathOnFileUriExposure")
                                                m.invoke(null)
                                            } catch (e: Exception) {
                                                e.printStackTrace()
                                            }
                                        }
                                        val uri = Uri.parse("smsto:")
                                        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "/tmp")
                                        withContext(Dispatchers.IO) {
                                            URL(photos[it + maxOf(0, (currentPage-1))].imageLink).openStream().copyTo(file.outputStream())
                                        }
                                        println(Uri.fromFile(file))
                                        println("HEREHRHERHERERHEHRERERERE")
                                        val shareIntent = Intent().apply {
                                            action = Intent.ACTION_SEND
                                            setData(uri)
                                            type="image/jpg"

                                            putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider",file))

                                        }
                                        context.startActivity(Intent.createChooser(shareIntent, null))
                                    }
                                })
                            }
                        }
                        if(dialog) {
                            var moveX by remember {mutableStateOf(0f)}
                            var moveY by remember {mutableStateOf(0f)}
                            var scale by remember {mutableStateOf(1.0f)}
                            var transformState = rememberTransformableState {
                                    zoomChange, offsetChange, rotationChange ->
                                scale *= zoomChange
                                moveX += offsetChange.x
                                moveY += offsetChange.y


                            }
                            Dialog(onDismissRequest = { dialog = false }) {
                                Column {
                                   Image(
                                        LoaderImage(photos[it + (maxOf(0, (currentPage)) * 6)].imageLink), contentDescription = "Stuff", modifier = Modifier
                                           .graphicsLayer(scaleX = scale, scaleY = scale, translationX = moveX, translationY = moveY)
                                           .transformable(state = transformState)
                                           .size(500.dp).clip(RoundedCornerShape(10.dp)))

                                }

                            }
                        }
                    }}
                }
                if(loadPagination == 1) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
                        generatePagination(maxOf(1, currentPage), totalPages = (photos?.size?.div(6.0)!!).toInt()).forEach {
                            if(it == -1) {
                                Text(text = "...", fontSize = 24.sp, modifier = Modifier.padding(10.dp))
                            } else {
                                Text(text = (it).toString(), fontSize = 24.sp, fontWeight = if(it == currentPage || (currentPage == 1 && it==1)) FontWeight.Bold else FontWeight.Normal,modifier = Modifier
                                    .clickable {

                                        currentPage = it
                                    }
                                    .padding(10.dp))
                            }

                        }
                    }
                }

            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyHorizontalGrid(rows = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(26.dp), verticalArrangement = Arrangement.spacedBy(26.dp), modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .padding(20.dp)) {
                    items(6) {
                        var dialog by remember {mutableStateOf(false)}
                        var contextMenu by remember {mutableStateOf(false)}
                        if(photos?.getOrNull((currentPage * 6) + it)?.votes != null) {
                            Box(modifier = Modifier
                                .size(320.dp)
                                .combinedClickable(onLongClick = {
                                    contextMenu = true

                                }, onClick = { dialog = true })
                               ) {

                               Image(LoaderImage(photos.get(it + (currentPage * 6)).imageLink), modifier = Modifier
                                    .matchParentSize().clip(RoundedCornerShape(10.dp)), contentDescription = "", contentScale = ContentScale.Crop, )
                                Column(modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .background(Color(0xAA000000))) {
                                    Text(text = "Votes" + photos?.get((currentPage * 6) + it)?.votes, color=  Color.White)
                                    Text(text = "Popularity" + photos?.get((currentPage * 6) + it)?.popularity, color = Color.White)
                                }

                                    DropdownMenu(expanded = contextMenu, onDismissRequest = { contextMenu = false    }) {
                                        DropdownMenuItem(
                                            text = { Text("Share")},
                                            onClick = {
                                                scope.launch {
                                                    if (Build.VERSION.SDK_INT >= 24) {
                                                        try {
                                                            val m =
                                                                StrictMode::class.java.getMethod("disableDeathOnFileUriExposure")
                                                            m.invoke(null)
                                                        } catch (e: Exception) {
                                                            e.printStackTrace()
                                                        }
                                                    }
                                                    val uri = Uri.parse("smsto")
                                                    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "/tmp")
                                                    withContext(Dispatchers.IO) {
                                                   URL(photos[it + ((currentPage-1)* 6)].imageLink).openStream().copyTo(file.outputStream())
                                                    }
                                                    println(Uri.fromFile(file))
                                                    println("HEREHRHERHERERHEHRERERERE")
                                                    val shareIntent = Intent().apply {
                                                        action = Intent.ACTION_SEND

                                                        type="image/jpg"

                                                        putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider",file))

                                                    }
                                                    context.startActivity(Intent.createChooser(shareIntent, null))
                                                }


                                            })
                                    }

                            }
                            if(dialog) {
                                var scale by remember {mutableStateOf(1.0f)}
                                var transformState = rememberTransformableState {
                                        zoomChange, offsetChange, rotationChange ->
                                    scale *= zoomChange

                                }
                                Dialog(onDismissRequest = { dialog = false }) {
                                    Column {

                                      Image(
                                            LoaderImage(imageLink = photos.get(it -1+ (currentPage * 6)).imageLink,), contentDescription = "Stuff", modifier = Modifier
                                                .graphicsLayer(scaleX = scale, scaleY = scale)
                                                .transformable(state = transformState)
                                                .size(500.dp).clip(RoundedCornerShape(10.dp)))

                                    }

                                }
                            }
                        }

                    }
                }
                if(loadPagination ==1) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
                        generatePagination(maxOf(1, currentPage), totalPages = (photos?.size?.div(6.0)!!).toInt()).forEach {
                            if(it == -1) {
                                Text(text = "...", fontSize = 24.sp, modifier = Modifier.padding(10.dp))
                            } else {
                                Text(text = (it).toString(), fontSize = 24.sp, fontWeight = if(it == currentPage) FontWeight.Bold else FontWeight.Normal,modifier = Modifier
                                    .clickable {

                                        currentPage = it
                                    }
                                    .padding(10.dp))
                            }

                        }
                    }
                }

            }
        }

    }


}


fun generatePagination(currentPage: Int, totalPages: Int): List<Int> {
    val pageList = mutableListOf<Int>()


    pageList.add(1)
    if(currentPage == 1 && totalPages > 1) {
        (2..minOf(totalPages, 3)).forEach {
            pageList.add(it)
        }
        if(totalPages - (currentPage + 2) > 1) {
            pageList.add(-1)
        }
    }
    else if (
        currentPage == totalPages && totalPages > 2
    ) {
        if((totalPages-2) - 1 > 1) {
            pageList.add(-1)
        }
        pageList.add(totalPages-2)
        pageList.add(totalPages -1)

    }
    else {
        if(currentPage -1 > 1) {
            pageList.add(-1)
        }
        if(!pageList.contains(currentPage -1)) {
                pageList.add(currentPage -1)
            }


        pageList.add(currentPage)
        pageList.add(currentPage + 1)
        if(totalPages - (currentPage + 1) > 1) {
            pageList.add(-1)
        }
    }
    if(!pageList.contains(totalPages)) {
        pageList.add(totalPages)
    }




    return pageList
}
