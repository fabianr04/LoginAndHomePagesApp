package com.cursojetpack.footballplayers.app.presentation.login.components.texts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//estructuracion del titulo de la app que se mostrara en el login
@Composable
fun MyTitle(modifier: Modifier, title:String ){
    Text(
        title, Modifier.padding(30.dp),
        fontSize = 48.sp,
        fontFamily = FontFamily.Cursive
    )
}