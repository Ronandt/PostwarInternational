package com.example.international_a_am

import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.ImageResult
import com.example.international_a_am.data.TicketItem
import com.example.international_a_am.data.WorldSkillsAPI
import com.example.international_a_am.data.screenshot
import com.example.international_a_am.data.showNotification
import com.example.international_a_am.ui.theme.LightGray

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TicketDetailsScreen(navController: NavController, id: String) {
    val view: View = LocalView.current
    val context = LocalContext.current
    var composableBounds by remember {
        mutableStateOf<Rect?>(null)
    }
    var permissions = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission() ) {


    }
    var ticket by remember {mutableStateOf<TicketItem?>(null)}
    var tickets = remember {mutableStateListOf<TicketItem>()}
    LaunchedEffect(key1 = Unit ) {
        val db = Database.getDb(context)
        WorldSkillsAPI().getTickets()?.let { tickets.addAll(it) }
        ticket = tickets.filter { t-> t.id == id }.get(0)
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissions.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissions.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissions.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }
Column(modifier = Modifier.fillMaxSize()) {
Text("Ticket Details", fontSize = 30.sp, fontWeight = FontWeight.SemiBold,modifier = Modifier.padding(start = 8.dp),)
Spacer(modifier = Modifier.height(20.dp))
Card(modifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 10.dp)
    .onGloballyPositioned {
        composableBounds = it.boundsInWindow()
    }, colors = CardDefaults.cardColors(LightGray)) {
    Column(modifier = Modifier.fillMaxWidth()){

        val imagePainterTest = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current).data(ticket?.imageLink).crossfade(true).placeholder(R.drawable.morecomplexpreloader).build(), contentScale = ContentScale.Crop)
        Box {
            Image(imagePainterTest, contentDescription = "", modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(), contentScale = ContentScale.Crop)
            Chip(onClick = { /*TODO*/ }, colors = ChipDefaults.chipColors(Color.Black), modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopEnd)) {
                ticket?.ticketType?.let { Text(text = it, color = Color.White) }
            }
        }

        Column(modifier = Modifier.padding(start= 10.dp)){
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)){
                Column {
                    Text(text = "Audience Name", fontWeight = FontWeight.Medium, color = Color.Gray)
                    ticket?.name?.let { Text(text = it, color = Color.DarkGray, fontWeight =FontWeight.Medium) }
                }
                Column {
                    Text(text = "Time", fontWeight = FontWeight.Medium, color = Color.Gray)
                    ticket?.time?.let { Text(text = it, color = Color.DarkGray, fontWeight =FontWeight.Medium) }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                Column {
                    Text(text = "Seat", fontWeight = FontWeight.Medium, color = Color.Gray)
                    Text(text = "${ticket?.seat?.split(" ")?.get(0)}", color = Color.DarkGray, fontWeight =FontWeight.Medium)
                }
                Column {
                    Text(text = "Row", fontWeight = FontWeight.Medium, color = Color.Gray)
                    Text(text = "${ticket?.seat?.split(" ")?.get(1)?.last()?.titlecase()}", color = Color.DarkGray, fontWeight =FontWeight.Medium)
                }
                Column {
                    Text(text = "Column", fontWeight = FontWeight.Medium, color = Color.Gray)
                    Text(text = "${ticket?.seat?.split(" ")?.get(2)?.last()?.titlecase()}", color = Color.DarkGray, fontWeight =FontWeight.Medium)
                }

            }

            Spacer(modifier = Modifier.height(14.dp))
        }

    }


}
    Spacer(modifier = Modifier.height(10.dp))
    Button(shape = RoundedCornerShape(10.dp),colors = ButtonDefaults.buttonColors(containerColor = Color.Black),modifier = Modifier.padding(start = 8.dp),onClick = {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissions.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }



        val result = composableBounds?.let { view.screenshot(it) }

       when(result) {
           is ImageResult.Error -> {
               Toast.makeText(context, "Issue", Toast.LENGTH_SHORT).show()
           }
           is ImageResult.Success -> {
               MediaStore.Images.Media.insertImage(context.getContentResolver(), result.data, ticket?.seat + " Ticket", "Ticket for whatever");

         //      Toast.makeText(context, "Downloaded", Toast.LENGTH_SHORT).show()
           }

           else -> {}
       }
        showNotification(context, "Your ticket has been downloaded", "Ticket: ${ticket?.seat}")







    }) {

        Text(text = "Download")
    }
}
}


