package com.cursojetpack.footballplayers.app.presentation.login.components.texts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//estructuracion del texto de contraseña olvidada
//en caso de usar inicio de sesion con email y contraseña
@Composable
fun ForgotPasswordText (modifier: Modifier) {
    Text(
        "Forgot Password?",
        Modifier.padding(2.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        color = Color.LightGray
    )
}