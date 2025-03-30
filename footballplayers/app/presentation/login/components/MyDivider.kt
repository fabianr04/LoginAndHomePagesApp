package com.cursojetpack.footballplayers.app.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//estructuracion de una divider para separar
//metodos de inicio de sesion
@Composable
fun MyDivider (text: String, modifier: Modifier) {
    Box(contentAlignment = Alignment.Center) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray
        )
        Box (Modifier.align(Alignment.Center)
            .background(MaterialTheme.colorScheme.background)) {
            Text(
                text,
                Modifier.align(Alignment.Center)
                    .padding(horizontal = 2.dp)
            )
        }
    }
}