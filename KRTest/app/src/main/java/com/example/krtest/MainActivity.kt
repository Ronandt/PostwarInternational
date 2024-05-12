package com.example.krtest

import android.os.Bundle
import android.widget.NumberPicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.PlainTooltipState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.krtest.ui.theme.KRTestTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KRTestTheme {
                var latest by remember {mutableStateOf(10)}
                LaunchedEffect(key1 = latest) {
                    println(latest)
                }
                Column {
                    Row {
                        AndroidView(factory = {
                            NumberPicker(it).apply {
                                this.maxValue = 30
                                this.minValue = 0
                                this.textSize = 30F

                                this.setOnValueChangedListener { numberPicker, i, i2 ->
                                    latest = i2
                                }
                            }


                        }, update = {

                            it.value = latest
                        })
                        AndroidView(factory = {
                            NumberPicker(it).apply {
                                this.maxValue = 30
                                this.minValue = 0
                                this.textSize = 30F
                                this.setOnValueChangedListener { numberPicker, i, i2 ->
                                    latest = i2
                                }
                            }


                        })
                        AndroidView(factory = {
                            NumberPicker(it).apply {
                                this.maxValue = 30
                                this.minValue = 0
                                this.textSize = 30F
                                this.setOnValueChangedListener { numberPicker, i, i2 ->
                                    latest = i2
                                }
                            }


                        })
                    }
                    //buttons for res
                    //textfield for res
                    //iconbutton etc try desc otherwise try text or textContains
                    //iterative is textcontains or iterate testTags
                    //

                    val tooltipstate = remember {PlainTooltipState()}
                    var currentValue = 130f
                    var oneUnit= 100f/200f
                    
                    Box {
                        Text(text = "$currentValue", modifier = Modifier.offset(x = ((currentValue * oneUnit) - 20).dp, y =-14.dp   ).background(Color.Black), color = Color.White)
                        Spacer(modifier = Modifier
                            .width(100.dp)
                            .height(20.dp).background(Color.LightGray))
                        Spacer(modifier = Modifier
                            .width((currentValue * oneUnit).dp)
                            .height(20.dp)
                            .background(Color.Black))
                    }
                 
                    
                    OutlinedTextField(value = "a", onValueChange = {}, enabled = false, textStyle = TextStyle(
                        Color.Black) , colors = TextFieldDefaults.outlinedTextFieldColors(disabledBorderColor = Color(0xAA000000)))
                    OutlinedTextField(value = "a", onValueChange = {}, enabled = true, textStyle = TextStyle(
                        Color.Black) , )

                    BasicTextField(value = "3", onValueChange =  {


                    }, keyboardOptions = KeyboardOptions())


                }



            }
        }
    }
}

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
    KRTestTheme {
        Greeting("Android")
    }
}