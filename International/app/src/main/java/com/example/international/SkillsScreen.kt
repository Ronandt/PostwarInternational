package com.example.international

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international.ui.theme.HotPink
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SkillsScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var skills = remember {
        JsonRetriever(context).skill()
    }
    var imageList = listOf(R.drawable.ws01, R.drawable.ws02, R.drawable.ws03,R.drawable.ws01, R.drawable.ws02, R.drawable.ws03)
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        skills?.size!!
    }

Column(modifier = Modifier.fillMaxWidth()) {
    HorizontalPager(state = pagerState, beyondBoundsPageCount = 1, modifier = Modifier
        .size(800.dp, 600.dp)
        .offset(x = 100.dp)) {
        Box(modifier = Modifier.size(800.dp, 600.dp)) {
            Image(painterResource(imageList[(0 until 5).random()]), contentDescription = null, modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)
            Column(modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(HotPink)
                .padding(vertical = 30.dp, horizontal = 15.dp)) {
                skills?.get(it)?.let { it1 -> Text(text = it1.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp) }
                Spacer(modifier = Modifier.height(14.dp))
                skills?.get(it)?.let { it1 -> Text(text = it1.description, color = Color.White, modifier = Modifier.width(350.dp)) }

            }
        }



    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically) {
        IconButton(enabled = pagerState.currentPage != 0, onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage -1)
            }
         }, modifier = Modifier.border(shape = CircleShape, width = 3.dp, color = HotPink)) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Left", tint = HotPink, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(1.dp))
        repeat(imageList.size) {
            val color = if(pagerState.currentPage == it) HotPink else Color.LightGray

            Spacer(modifier = Modifier
                .size(18.dp)
                .clip(CircleShape)
                .background(color))
        }
        Spacer(modifier = Modifier.width(1.dp))
        IconButton(enabled = pagerState.currentPage < skills?.size!!,onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage +1)
            }

        }, modifier = Modifier.border(shape = CircleShape, width = 3.dp, color = HotPink)) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Left", tint = HotPink    , modifier = Modifier.size(20.dp))
        }
    }
}
}