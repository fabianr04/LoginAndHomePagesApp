package com.cursojetpack.footballplayers.app.presentation.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//estructuracion de un dialogo de error en caso de que los
//datos ingresados sean incorrectos al usar email y contraseña
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit
) {
    if (showDialog){
        BasicAlertDialog(onDismissRequest = {
            onDismissRequest()
        }) {
            Card(
                Modifier.height(100.dp)
                    .width(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column (
                    Modifier.fillMaxSize()
                        .padding(start = 24.dp, top = 24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Datos inválidos")
                    TextButton(
                        onClick = onDismissRequest,
                        Modifier
                            .align(Alignment.End)
                            .padding(end = 16.dp).
                            clip(RoundedCornerShape(24.dp)),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}