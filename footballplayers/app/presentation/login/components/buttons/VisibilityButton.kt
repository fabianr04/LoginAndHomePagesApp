package com.cursojetpack.footballplayers.app.presentation.login.components.buttons


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

//estructuracion del boton de visibilidad de la contraseña
//en caso de usar inicio de sesion con email y contraseña
@Composable
fun VisibilityButton (@DrawableRes image: Int, passwordVisible: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick,
        Modifier.shadow(
            elevation = 4.dp,
            shape = CircleShape,
            clip = true,
            spotColor = Color.Black
        )
            .background(MaterialTheme.colorScheme.secondary)
            .padding(2.dp)
            .size(40.dp)
    ) {
        Icon(
            painterResource(image),
            contentDescription = "visibility icon",
            Modifier
                .size(26.dp)
                .background(MaterialTheme.colorScheme.secondary),
            tint = Color.Black
        )
    }
}