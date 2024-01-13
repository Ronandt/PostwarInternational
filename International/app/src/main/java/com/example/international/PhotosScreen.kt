package com.example.international

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.international.ui.theme.HotPink

@Composable
fun PhotosScreen(navController: NavController) {
    var dialog by remember {mutableStateOf(-1)}

    Spacer(modifier = Modifier.height(10.dp))
Row(modifier = Modifier
    .fillMaxWidth()
    .padding(end = 20.dp, top = 20.dp)) {
    Column(modifier = Modifier.padding(end = 10.dp)) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.width(480.dp)) {
            items(4) {
                Image(painter = painterResource(id = R.drawable.ws04), contentDescription = "", modifier = Modifier
                    .size(230.dp)
                    .clickable {
                        dialog = R.drawable.ws04
                    }, contentScale = ContentScale.Crop, )
            }


        }
        Spacer(modifier = Modifier.height(10.dp))
        Image(painter = painterResource(id = R.drawable.ws06), contentDescription = "", modifier = Modifier
            .width(480.dp)
            .height(230.dp)
            .clickable {
                dialog = R.drawable.ws06
            }, contentScale = ContentScale.Crop)
    }
    Column() {
        Image(painter = painterResource(id = R.drawable.ws05), contentDescription = "", modifier = Modifier
            .size(474.dp)
            .clickable {
                dialog = R.drawable.ws05
            }, contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Image(painter = painterResource(R.drawable.ws04), contentDescription ="", contentScale = ContentScale.Crop    , modifier = Modifier
                .size(230.dp)
                .clickable {
                    dialog = R.drawable.ws04
                })
            Image(painter = painterResource(R.drawable.ws04), contentDescription ="" , contentScale = ContentScale.Crop , modifier = Modifier
                .size(230.dp)
                .clickable {
                    dialog = R.drawable.ws04
                })
        }
    }

}

        AnimatedVisibility(visible = dialog != -1, enter = expandIn(), exit = scaleOut()) {
            Dialog(onDismissRequest = { dialog = -1}) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    IconButton(onClick = { dialog = -1}) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = HotPink)
                    }
                    Image(painter =  painterResource(if(dialog == -1) R.drawable.ic_launcher_background else  dialog), contentDescription ="Image", modifier = Modifier.fillMaxSize())
                }
            }
        }



}