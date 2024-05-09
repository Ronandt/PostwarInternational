package com.example.healthapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthapp.ui.theme.Dark
import com.example.healthapp.ui.theme.LightGray

@Composable
fun ApplicationTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, leadingIcon:@Composable  () -> Unit ={}   , trailingIcon:@Composable  () -> Unit  = {},clickable: Boolean, onClick: () -> Unit = {}) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange, modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        label = { Text(placeholder, color = Color.DarkGray) },
        trailingIcon = {
          trailingIcon()

        },leadingIcon = {
                    leadingIcon()


        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = LightGray,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent
        ), enabled = !clickable


    )

}



@Composable
fun ApplicationTextButton(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick    , colors = ButtonDefaults.buttonColors(backgroundColor = Dark), modifier = Modifier.fillMaxWidth()) {
//
        Text(text = text,    fontWeight = FontWeight.SemiBold, color = Color.White, modifier = Modifier.padding(6.dp   ))
    }
}

@Composable
fun BlackTextButton(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick    , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black), modifier = Modifier.fillMaxWidth()) {
//
        Text(text = text,    fontWeight = FontWeight.SemiBold, color = Color.White, modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun WhiteTextButton(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick    , colors = ButtonDefaults.buttonColors(backgroundColor = Color.White), modifier = Modifier.fillMaxWidth()) {
//
        Text(text = text,    fontWeight = FontWeight.SemiBold, color = Color.Black, modifier = Modifier.padding(4.dp))
    }
}


@Composable
fun ApplicationDialog(
    title: String,
    content: @Composable () -> Unit,
    onComplete: () -> Unit,
    onCancel: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White, shape = RoundedCornerShape(10.dp))
        .padding(10.dp)) {
        Text(text = title, fontWeight = FontWeight.SemiBold)
                content()
        Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(10.dp    )) {
            BlackTextButton(text = "Complete") {
                onComplete()
            }
            WhiteTextButton(text = "Cancel") {
                onCancel()
            }
        }

    }
}