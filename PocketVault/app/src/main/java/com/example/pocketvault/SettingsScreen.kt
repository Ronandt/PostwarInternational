package com.example.pocketvault

import android.content.ContentValues
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import java.io.File
import java.util.UUID

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val passwordData = PasswordData(context)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        var getData = rememberLauncherForActivityResult(contract = ActivityResultContracts.CreateDocument("application/json")) {
            if (it != null) {
                context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                var d = passwordData.moshi.adapter<List<Password>>().fromJson( context.contentResolver.openInputStream(it)?.bufferedReader()?.readText())
                d?.forEach {
                    passwordData.putPasswords(it)
                }

               Toast.makeText(context, "Imported data!", Toast.LENGTH_LONG).show()
            }

        }
        var vuewData = rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) {
            if (it != null) {
                context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                var d = passwordData.moshi.adapter<List<Password>>().fromJson( context.contentResolver.openInputStream(it)?.bufferedReader()?.readText())
                println(d)

                if (d != null) {
                    passwordData.putPasswordss(d)
                }


                Toast.makeText(context, "Imported data!", Toast.LENGTH_LONG).show()
            }
            println(it?.scheme)

        }
        var perms = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {


        }
        LaunchedEffect(key1 = Unit) {
            perms.launch(android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)
        }
    Header(text = "Settings")
    Spacer(modifier = Modifier.height(10.dp))
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(6.dp )) {
        SimpleApplicationCard(header = "Import backup data" ) {
            vuewData.launch(arrayOf("application/json"))

        }
        SimpleApplicationCard(header = "Export data to JSON") {


            val adapter = passwordData.moshi.adapter<List<Password>>()



          var a =   context.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, ContentValues().apply {
                put(MediaStore.Downloads.DISPLAY_NAME,"${UUID.randomUUID()}.json" )

            })


               if (a != null) {
                   context.contentResolver.openOutputStream(a).apply {
                       this?.write( adapter.toJson(passwordData.getPasswords()).toByteArray())
                   }

               }




            println(adapter.toJson(passwordData.getPasswords()))
            Toast.makeText(context, "Exported", Toast.LENGTH_LONG).show()

        }
    }
    Spacer(modifier = Modifier.weight(1f))
    DangerApplicationCard(header = "Log out") {
        passwordData.clearSession()
        navController.navigate("login") {
            popUpTo("login")
        }

    }



}
}