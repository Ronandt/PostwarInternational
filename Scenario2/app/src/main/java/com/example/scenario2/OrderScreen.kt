package com.example.scenario2

import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.scenario2.ui.theme.appBarColor
import com.example.scenario2.ui.theme.listItemsColor
import com.example.scenario2.ui.theme.primaryButtonColor
import kotlinx.coroutines.launch
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProfileTab(navController: NavController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    val json= sharedPref.getString("info", "{}")?.let { JSONObject(it) }
    var imagePicker = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        if(it != null) {
            with(sharedPref.edit())
            {
                this.putString(sharedPref.getString("info", "{}"), it.toString())
                this.apply()
            }

            context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {


        Card(modifier = Modifier.fillMaxWidth()) {
            if(sharedPref.getString(sharedPref.getString("info", "{}"), "")!="") {

                Image( BitmapFactory.decodeStream(sharedPref.getString(sharedPref.getString("info", "{}"), "")?.toUri()
                    ?.let { context.contentResolver.openInputStream(it) }).asImageBitmap(), contentDescription = "Profile", modifier = Modifier
                    .requiredSize(70.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .clickable {
                        imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }, contentScale = ContentScale.Crop)
            } else {
                Image(painter = painterResource(R.drawable.human), contentDescription = "Profile", modifier = Modifier
                    .requiredSize(70.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .clickable {
                        imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }, contentScale = ContentScale.Crop)
            }

        }
        Text(text = "Rewards", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        androidx.compose.material3.Card(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),onClick = { /*TODO*/ }, colors = CardDefaults.cardColors(containerColor = listItemsColor)) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Text(text = json?.getJSONObject("result")?.getJSONObject("profile")?.getInt("points").toString() + "Points", fontWeight = FontWeight.Bold, fontSize = 22.sp)

            }



        }
        Spacer(modifier = Modifier.height(20.dp))


        Text(text = "Favourite Drink", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        (0 until json?.getJSONObject("result")?.getJSONObject("profile")?.getJSONArray("prefered_order")?.length()!!).forEach {

            androidx.compose.material3.Card(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),onClick = { /*TODO*/ }, colors = CardDefaults.cardColors(containerColor = listItemsColor)) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(R.drawable.bubbletea), contentDescription = "" )
                    Column {
                        Text(text  =          json?.getJSONObject("result")?.getJSONObject("profile")?.getJSONArray("prefered_order")!!
                            .getJSONObject(it).getString("name"), fontSize = 12.sp)
                        Text(text =          json?.getJSONObject("result")?.getJSONObject("profile")?.getJSONArray("prefered_order")!!
                            .getJSONObject(it).getJSONArray("cust").toString(), fontSize = 12.sp)

                    }
                    SelectionButton(text = "Order", selected = true , small = true) {

                    }

                }





            }
            Spacer(modifier = Modifier.height(20.dp))
        }



Spacer(modifier = Modifier.height(20.dp))
        androidx.compose.material3.Card(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),onClick = {  }, colors = CardDefaults.cardColors(containerColor = listItemsColor)) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                Image(painter = painterResource(R.drawable.bubbletea), contentDescription = "", modifier = Modifier.size(60.dp))
                Column {
                    Text(text  = "A need to feedback on the service?", fontSize = 12.sp)


                }
                SelectionButton(text = "Feedback", selected = true , small = true) {
                    navController.navigate("feedback")
                }

            }





        }



    }
    
}
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun OrderScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    var sizeType by remember {mutableStateOf("")}
    var pagerState = rememberPagerState(
        initialPage = 1,
        initialPageOffsetFraction = 0f
    ) {
       3
    }
    var selection = listOf("PROFILE", "ORDER", "HISTORY")
    var bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(containerColor = Color.White,scaffoldState = bottomSheetScaffoldState, sheetPeekHeight = if(pagerState.currentPage == 1)120.dp else 0.dp    ,topBar = {
        androidx.compose.material.TopAppBar(backgroundColor = appBarColor) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "OrderMyT", fontWeight = FontWeight.Bold)
                IconButton(onClick = {navController.navigate("checkout")  }) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Checkout", tint = Color.Black)
                }
            }
        }
    },sheetContent = {

        if(pagerState.currentPage == 1) {
            Column(Modifier.requiredHeight(400.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween,) {
                    Text(text = "Total\n$7.6")
                    SelectionButton(text = "ADD TO ORDER", selected = true) {

                        //do stuff here
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween,) {

                    Text(text = "+$7.6")
                    Text(text = "Medium size")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween,) {
                    Text(text = "+$0")
                    Text(text = "Chocolate")
                }
            }
        }


    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            TabRow(selectedTabIndex = pagerState.currentPage, containerColor = appBarColor, indicator = {

                    tabPositions -> TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]
                ),
                color = Color.White
            )
            }) {
                selection.forEachIndexed { num, value ->
                    Tab(modifier = Modifier.padding(vertical = 16.dp),selected = num == pagerState.currentPage , onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(num)
                        }
                    }) {
                        Text(text = value, fontWeight = FontWeight.SemiBold, color = if(num == pagerState.currentPage) Color.Black else Color(0xAA000000))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))



            HorizontalPager(state = pagerState) {
                when(it) {
                    0 -> {
                        ProfileTab(navController)
                    }
                    1 -> {
                        OrderTab(navController)
                    }
                    2 -> {
                        HistoryTab(navController)
                    }
                }
            }

    }

}




}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun OrderTab(navController: NavController) {
    val scope = rememberCoroutineScope()
    var quantity by remember {mutableStateOf(1)}
    var teaType by remember {mutableStateOf(0)}
    var size by remember {mutableStateOf("Medium",)}
    var flavour by remember {mutableStateOf("Vanilla")}
    var totalPrce by remember {mutableStateOf(0)}
    var expanded by remember {mutableStateOf(false)}

    val bubbleTeaSelection = listOf("Bubble Milk Tea with Ice Cream", "Red Bull Milk Tea with Yakult", "Random juice ")
    Column(modifier =  Modifier) {
        LazyRow(horizontalArrangement=   Arrangement.spacedBy(16.dp)) {
            items(bubbleTeaSelection.size) {
                Card(onClick = { teaType = it }, modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp), verticalArrangement = Arrangement.Center) {
                        Image(painter = painterResource(R.drawable.bubbletea), contentDescription = "Image", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)


                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(24.dp))


        Column(modifier = Modifier.padding(horizontal = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = bubbleTeaSelection[teaType], fontSize = 24.sp, modifier= Modifier.padding(bottom = 50.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Quantity")
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded} ) {
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        (1..4).forEach {
                            DropdownMenuItem(text = { Text(it.toString()) }, onClick = { quantity = it})
                        }

                    }
                    Row(modifier = Modifier

                        .menuAnchor()) {
                        Text(text = quantity.toString())
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Open")
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Size")
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SelectionButton(text = "Medium", selected =size == "Medium" ) {
                        size = "Medium"
                    }
                    SelectionButton(text = "Large", selected =  size == "Large") {
                        size = "Large"
                    }
                }


            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Ice Cream Flavour", modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)) {
                listOf("Vanilla", "Chocolate", "Durain").forEach {
                    SelectionButton(text = it, selected = flavour == it) {
                        flavour = it
                    }
                }

            }

        }
    }



}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryTab(navController: NavController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    val json= sharedPref.getString("info", "{}")?.let { JSONObject(it) }


Column(modifier = Modifier
    .fillMaxSize()
    .padding(horizontal = 18.dp)) {
    Text(text = "Recent Orders", fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(10.dp))
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(    json?.getJSONObject("result")?.getJSONArray("history")?.length()!!) {
            androidx.compose.material3.Card(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),onClick = { /*TODO*/ }, colors = CardDefaults.cardColors(containerColor = listItemsColor)) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Image(painter = painterResource(R.drawable.bubbletea), contentDescription = "" )
                    Column {
                        Text(text  = json?.getJSONObject("result")?.getJSONArray("history")?.getJSONObject(it)?.getString("name")!!, fontSize = 12.sp)
                        Text(text = json?.getJSONObject("result")?.getJSONArray("history")?.getJSONObject(it)?.getJSONArray("cust").toString()!!, fontSize = 12.sp)

                    }
                    Column {
                        Text(text = "$5.6", fontWeight = FontWeight.SemiBold)
                        SelectionButton(text = "Order", selected = true , small = true) {

                        }
                    }


                }





            }
        }

    }


}
}

@Composable
fun SelectionButton(text: String, selected: Boolean,  small: Boolean = false, onSelect: () -> Unit) {
    Button(onClick = onSelect, colors = ButtonDefaults.buttonColors(backgroundColor = if(selected) primaryButtonColor else Color.White)) {
        Text(text = text.uppercase(), fontWeight = FontWeight.Bold, fontSize = if(small) 12.sp else 16.sp)
    }
}