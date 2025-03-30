package com.cursojetpack.footballplayers.app.presentation.login.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cursojetpack.footballplayers.R
import com.cursojetpack.footballplayers.app.presentation.login.LoginViewModel

//estructuracion del boton de inicio de sesion
@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(38.dp)),
        shape = RoundedCornerShape(38.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray
        )
    ) {
        Row(
            Modifier.fillMaxWidth()
                /*.padding(6.dp)*/,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ){
            Text("Log In")

            Icon(
                painterResource(R.drawable.google_icon),
                contentDescription = "google-icon",
                Modifier.size(54.dp),
                tint = Color.Unspecified
            )
        }
    }
}



