package com.example.pocketvault

import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    var data = remember{mutableStateListOf<Note>()}
    LaunchedEffect(key1 = Unit ) {
        data.addAll(AppDatabase.getDb(context).noteDao().getAll())


    }
    val gson = Gson()
    var picker = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        println(it?.let { it1 -> context.contentResolver.openInputStream(it1)?.bufferedReader()?.readText() })
    }
Column(modifier = Modifier
    .fillMaxSize()
    .background(Color.Black)) {

    Header(text = "Settings")
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(6.dp )) {
        ApplicationCard() {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), contentAlignment = Alignment.Center) {
                Text(text = "Import backup data", color = Color.White, fontWeight = FontWeight.SemiBold)
            }

        }
        ApplicationCard(
            onClick = {
                val uri = context.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, ContentValues().apply {

                })
                if (uri != null) {
                   context.contentResolver.openOutputStream(uri)?.write(
                       gson.toJson(data).toByteArray()
                    )
                }


            }

        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), contentAlignment = Alignment.Center) {
                Text(text = "Export data to json ", color = Color.White, fontWeight = FontWeight.SemiBold)
            }

        }
    }
    Spacer(modifier = Modifier.weight(1f))
    ApplicationCard({

        println("HEllo?")
        picker.launch("*/*")}) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), contentAlignment = Alignment.Center) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp   )) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                Text(text = "Logout", fontWeight = FontWeight.SemiBold, color  =Color.Red)
            }

        }

    }

}
}