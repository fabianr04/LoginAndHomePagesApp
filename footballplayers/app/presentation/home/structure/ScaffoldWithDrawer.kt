package com.cursojetpack.footballplayers.app.presentation.home.structure

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cursojetpack.footballplayers.app.presentation.login.LoginViewModel
import kotlinx.coroutines.launch

/*este composable se encarga de montar la estructura scaffold-drawerContent
  y solicitar la vista interna del scaffold como screen, pasando como
  parametro de screen el paddingValues que recibe el scaffold, es decir,
  el innerPadding*/

/*para crear un drawerContent se debe crear la funcion del scaffold como
  ScaffoldWithDrawer, crear un ModalNavigationDrawer y pasarle como content
  un scaffold. los parametros que se le debe dar al ModalDrawer son
  drawerState de tipo DrawerState (la variable con el remember) y drawerContent
  que es un composable ModalDrawerSheet al que se le debe de pasar la funcion
  MyDrawerContent*/

@Composable
fun ScaffoldWithDrawer(
    navigationController: NavHostController,
    loginViewModel: LoginViewModel,
    onSignedOut: () -> Unit,
    screen: @Composable (PaddingValues) -> Unit
) {
    LaunchedEffect(key1 = true) {
        loginViewModel.loadUserProfile()
    }

    val isLoading by loginViewModel.isLoading.observeAsState()
    val userName by loginViewModel.userName.observeAsState()
    val userPhoto by loginViewModel.userPhoto.observeAsState()
    Log.d("ScaffoldWithDrawer", "userName: $userName, userPhoto: $userPhoto")

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                MyDrawerContent(
                    userName,
                    userPhoto,
                    onClickSignOut = {
                        onSignedOut()
                    },
                    onClickDrawer = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }
    ) {
        if (isLoading == true) {
            Card {
                CircularProgressIndicator(
                    strokeWidth = 8.dp
                )
            }
        } else {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopBar(
                        onClickDrawer = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    )
                },
                bottomBar = {
                    BottomBar(onClickItem = {
                        navigationController.navigate(it)
                    })
                },
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }
            ) { innerPadding ->
                screen(innerPadding)
            }
        }
    }

}

/*
@Composable
fun MyFAB() {
    FloatingActionButton(
        onClick = { },
        shape = CircleShape,
        modifier = Modifier.shadow(
            elevation = 4.dp,
            shape = CircleShape,
            clip = true
        )
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}*/
