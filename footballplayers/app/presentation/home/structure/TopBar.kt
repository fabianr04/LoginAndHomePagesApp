package com.cursojetpack.footballplayers.app.presentation.home.structure

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.cursojetpack.footballplayers.app.data.Routes.*


//estructuracion de la TopBar de la pantalla principal
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onClickDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                "Players"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onClickDrawer()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "menu"
                )
            }
        }
    )
}