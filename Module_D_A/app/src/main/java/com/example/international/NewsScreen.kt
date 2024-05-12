package com.example.international

import android.media.MediaPlayer
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.international.ui.theme.DarkBlue
import com.example.international.ui.theme.DarkGradientBlue
import com.example.international.ui.theme.Dim
import com.example.international.ui.theme.GradientBlue
import com.example.international.ui.theme.MediumBlue
import com.example.international.ui.theme.VeryDarkBlue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NewsScreen(navController: NavController, title: String, newsTitle: String?, skillName: String?, startDate: String?, endDate: String?, onShadow: (Boolean) -> Unit) {
    val context = LocalContext.current
    val jsonRetriever = JsonRetriever(context)

    var news = remember { mutableStateListOf<NewsItem?>()}
    LaunchedEffect(key1 = Unit ) {
        println("HERE")
        println(WorldSkillsAPI().getNewsInfo())
      var cache =  WorldSkillsAPI().getNewsInfo()
        (0..cache?.toList()?.map {it.subcategories}?.size!!).forEach {
            cache?.toList()?.map {it.subcategories}?.getOrNull(it)?.filter { (title == "Search Results"&& skillName?.lowercase()
                .toString() in it.subcategory.lowercase()) || it.subcategory == title }?.map {
                println(it)
                it.newsItems }
                ?.getOrNull(0)?.filter{(startDate == null || convertMMMddyyyyToUnixTime(it.time.split(" ").drop(1).joinToString(" ")) in convertddMMyyyyToUnixTime(startDate)..endDate?.let { it1 ->
                    convertddMMyyyyToUnixTime(
                        it1
                    )
                }?.toInt()!!)}?.let { news.addAll(it.filter

                {
                    println(it)
                    title!= "Search Results" || ( newsTitle?.lowercase().toString() in it.title.lowercase() ) }

                ) }
        }


    }
    Column(Modifier.semantics {
        testTagsAsResourceId = true
    }) {

        Column(modifier = Modifier
            .fillMaxSize()
            ) {
            Box(modifier = Modifier.fillMaxWidth()) {

                Image(painter = painterResource(R.drawable.cloud_cop), contentDescription = "Skill image", modifier = Modifier.testTag("test")
                    .blur(10.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .graphicsLayer {

                    }, contentScale = ContentScale.Crop, )
                Spacer(modifier = Modifier
                    .matchParentSize()
                    .background(Dim))
                Column( modifier = Modifier.align(Alignment.Center)) {
                    Image(painter = painterResource(R.drawable.cube_3), contentDescription = null, modifier = Modifier
                        .size(34.dp)
                        .align(Alignment.CenterHorizontally))
                    
                    Text("$title", fontWeight = FontWeight.SemiBold, fontSize = 24.sp, color = Color.White)

                }

            }

            Spacer(modifier = Modifier.height(14.dp).testTag("how"))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier =  Modifier.testTag("col").padding(10.dp)) {
                items(news.size) {

                    var dialogOpen by remember {mutableStateOf(false)}
                    Column(modifier= Modifier.then(if(it + 1 == 10) {println("hit"); Modifier.semantics {testTagsAsResourceId = true}.testTag("target")} else Modifier)
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    GradientBlue,
                                    DarkGradientBlue
                                )
                            )
                        )
                        .clickable {
                            dialogOpen = true
                            onShadow(true)
                        }) {
                        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "${news[it]?.title}", color = Color.White, fontSize =20.sp, modifier = Modifier.testTag(
                                    "title".toString()).width(320.dp))
                                Text(text = (it+1).toString(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)


                            }

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "${title}", color = Color.White, fontSize = 14.sp)
                                Text(text = "${news[it]?.time}", color = Color.White, fontSize = 14.sp)
                            }
                        }
                 

                    }
                    if(dialogOpen) {
                        Dialog(onDismissRequest = { dialogOpen = false
                            onShadow(false)}) {
                            news[it]?.description?.let { it1 ->
                                news[it]?.time?.let { it2 ->
                                    news[it]?.title?.let { it3 ->
                                        ArticleDialog(
                                            it3, it2,
                                            it1
                                        ) {
                                            dialogOpen = false
                                            onShadow(false)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }




}

@Composable
fun ArticleDialog(title: String, datetime: String, description: String, close: () -> Unit) {
    var textSize by remember {mutableStateOf(16.sp)}
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(650.dp)
        .background(Color.White)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(DarkBlue), verticalAlignment = Alignment.CenterVertically) {

            Text(text = datetime, color = Color.White)
            Image(painter = painterResource(R.drawable.worldskills), contentDescription = "Worldsills", modifier = Modifier.size(60.dp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { close() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close", tint = Color.White, modifier = Modifier.size(28.dp))
            }

        }

        Text(text = title, fontWeight = FontWeight.Bold, color = DarkBlue, textAlign = TextAlign.Center, fontSize = 22.sp, modifier = Modifier.padding(vertical = 14.dp))

        Text(text = description, fontSize = textSize,modifier = Modifier.padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = {
                val player = MediaPlayer.create(context, R.raw.pop)
                player.start()
                textSize =  textSize.times(0.8)}) {
                Icon(painterResource(id = R.drawable.baseline_minimize_24), contentDescription = "Minimise " )
            }
            IconButton(onClick = { textSize = textSize.times(1.2)}, modifier = Modifier.testTag("l").onGloballyPositioned {
                println(it.positionInWindow().x)
                println(it.positionInWindow().y)
            }) {
                val player = MediaPlayer.create(context, R.raw.pop)
                player.start()
                Icon(Icons.Default.Add, contentDescription = "Plus" )
            }
        }
    }
}


fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val date: Date = inputFormat.parse(inputDate) ?: Date()
    return outputFormat.format(date)
}
fun convertMMMddyyyyToUnixTime(dateString: String): Long {
    val format = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
    val date = format.parse(dateString)
    return date.time / 1000 // converting milliseconds to seconds for Unix time
}

fun convertddMMyyyyToUnixTime(dateString: String): Long {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val date = format.parse(dateString)
    return date.time / 1000 // converting milliseconds to seconds for Unix time
}
//this is essentailly String -> SimpleDateFormat.parse -> date unix value

fun convertUnixTimeToDDMMYYYY(unixTime: Long): String {
    val date = Date(unixTime * 1000) //if seconds multiply by 1000
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return format.format(date)

    //Int -> Date unix value -> Simpledateformat.format

}

//Use SimpleDateFormat("string").parse(datestring) = time (Date to unix)
//use yyyy
//mm is minute MM is month
//DD -> Day in a year, dd -> day in a month