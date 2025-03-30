package com.cursojetpack.footballplayers.app.presentation.home.structure

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkAnnotation.Url
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

//estructuracion del DrawerContent que formara parte de la pantalla principal
@Composable
fun MyDrawerContent(
    username: String?,
    userPhoto: Uri?,
    //recibira una accion para el boton sign Out
    onClickSignOut: () -> Unit,
    //recibira una accion para el boton del menu (cierre del Drawer)
    onClickDrawer: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            Spacer(Modifier.width(4.dp))
            if (username != null && userPhoto != null) {
                val formatedUsername = username.replaceFirstChar { it.uppercase() }

                Text(
                    text = "Logged as: $formatedUsername",
                    minLines = 2,
                    fontSize = 12.sp,
                    modifier = Modifier.width(190.dp)
                )
                Spacer(Modifier.weight(1f))
                AsyncImage(
                    model = userPhoto,
                    contentDescription = "User Photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .shadow(elevation = 20.dp, shape = CircleShape)
                )

            }
        }
        Spacer(Modifier.height(32.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = { onClickSignOut() }, colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Sign Out")
            }


        }
    }

}