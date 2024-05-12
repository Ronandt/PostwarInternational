package com.example.worldskillssingapore

import android.content.Intent
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.worldskillssingapore.ui.theme.RedColor
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    var data = LoginSharedPreference(context).retrieveLogin()

    var bottomSheet = rememberModalBottomSheetState()
    var hoistCameraData by remember {mutableStateOf<String?>(null)}
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        bottomSheet.hide()
    }
    val cameraLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
      if(it) {
          hoistCameraData?.let { it1 -> LoginSharedPreference(context).storeImage(it1) }
      }
    }
    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        if (it != null) {

            context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            LoginSharedPreference(context).storeImage(it.toString())


        }
    }
    var bottomSheetState by remember {mutableStateOf(false)}
    if(bottomSheetState) {
        ModalBottomSheet(onDismissRequest = {
            scope.launch {bottomSheet.hide()
            bottomSheetState = false
            } }, sheetState = bottomSheet) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(14.dp    )) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = {
                        var randomUUID = UUID.randomUUID().toString()
                        var file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), randomUUID)
                        cameraLauncher.launch(
                            FileProvider.getUriForFile(context, context.packageName + ".provider", file)

                        )
                    hoistCameraData = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), randomUUID).toUri().toString()
                    }

                    ) {
                        Text(text = "Camera", fontSize = 20.sp, color = Color.Blue)

                    }
                }
                Divider()
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = {
                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

                    }) {
                        Text(text = "Photo Library", fontSize = 20.sp, color = Color.Blue)

                    }
                }
                Divider()
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = {
                        scope.launch {
                            bottomSheet.hide()
                        }


                    }) {
                        Text(text = "Cancel", fontSize = 20.sp, color = Color.Blue, fontWeight = FontWeight.SemiBold)

                    }
                }
                Divider()

            }

        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(RedColor), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "My Profile", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 30.sp  )
            Spacer(modifier = Modifier.height(100.dp))
        }
        Column(modifier = Modifier.padding(10.dp    )) {
            Box(modifier = Modifier
                .size(140.dp)
                .offset(y = -80.dp, x = 120.dp)) {
                if(LoginSharedPreference(context).getImage() != null) {
                    AsyncImage(model = LoginSharedPreference(context).getImage()?.toUri(), contentDescription = "Profile picture", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize() )
                } else {
                    Image(painter = painterResource(R.drawable.profilepicture), contentDescription = "Profile picture", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize() )
                }

                IconButton(onClick = {
                    scope.launch {
                        bottomSheet.expand()
                        bottomSheetState = true
                    }
                   }, modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 20.dp, y = 20.dp), colors = IconButtonDefaults.filledIconButtonColors(Color.White) ) {
                    Icon(painterResource(R.drawable.outline_camera_alt_24) , contentDescription = "Camera" )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .background(Color.White), verticalArrangement = Arrangement.spacedBy(5.dp  )) {
             Column(modifier = Modifier.padding(10.dp   ), verticalArrangement = Arrangement.spacedBy(10.dp)){
                 Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                     Row(horizontalArrangement = Arrangement.spacedBy(8.dp  )) {
                         Icon(imageVector = Icons.Default.Person, contentDescription = "Person" , tint= Color.DarkGray)
                         Text(text = "Email")
                     }
                     Text(text = "${data?.email}")
                 }
                 Divider()
                 Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                     Row(horizontalArrangement = Arrangement.spacedBy(8.dp  )) {
                         Icon(painterResource(R.drawable.baseline_business_24), contentDescription = "Person", tint = Color.DarkGray)
                         Text(text = "Organisation")
                     }
                     Text(text = "${data?.orgid}")
                 }
                 Divider()
                 Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                     Row(horizontalArrangement = Arrangement.spacedBy(8.dp  )) {
                         Icon(imageVector = Icons.Default.Lock, contentDescription = "Person",tint= Color.DarkGray )
                         Text(text = "Password")
                     }
                     TextButton(onClick = { navController.navigate("changePassword") }) {
                         Text(text = "Change Password", color = Color.Blue  )
                     }

                 }
                 Divider()
             }



            }
            Spacer(modifier = Modifier.height(14.dp))
            Column(modifier = Modifier.background(Color.White)) {
              Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("login") {
                            this.popUpTo("login")
                        }
                    }) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp   )) {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Exit")

                        Text(text = "Logout")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }


        }
   
    }
}