package com.cursojetpack.footballplayers.app.presentation.home.structure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cursojetpack.footballplayers.app.data.Routes.*

@Composable
fun BottomBar(onClickItem: (String) -> Unit) {

    val selectedItem by remember { mutableIntStateOf(0) }

    BottomAppBar(
        Modifier
            .fillMaxWidth()
            .height(60.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                onClick = {
                    onClickItem("")
                }
            ) {
                if (selectedItem == 0) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home"
                    )
                }
                /*else {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "OutlinedHome"
                    )
                }*/
            }
        }
    }
}
            /*IconButton(onClick = {
                selectedItem = 1
            }
            ) {
                if (selectedItem == 1) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "AddCircle"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "OutlinedAddCircle"
                    )
                }
            }
            IconButton(onClick = {
                selectedItem = 2
            }
            ) {
                if (selectedItem == 2) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Account"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "OutlinedAccount"
                    )
                }
            }*/
