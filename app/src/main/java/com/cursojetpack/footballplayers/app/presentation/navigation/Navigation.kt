package com.cursojetpack.footballplayers.app.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cursojetpack.footballplayers.app.data.Routes.*
import com.cursojetpack.footballplayers.app.presentation.home.HomeScreen
import com.cursojetpack.footballplayers.app.presentation.home.HomeViewModel
import com.cursojetpack.footballplayers.app.presentation.home.structure.ScaffoldWithDrawer
import com.cursojetpack.footballplayers.app.presentation.login.LoginScreen
import com.cursojetpack.footballplayers.app.presentation.login.LoginViewModel

//navegacion de la aplicacion
@Composable
fun MyNavHost(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel
) {

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        //uso de las clases Routes para la navegacion
        composable<HomeRoute>{
            ScaffoldWithDrawer(
                navController,
                loginViewModel,
                onSignedOut = {
                    //cierre de sesion y navegacion a la pantalla de login
                    loginViewModel.signOut(){
                        navController.navigate(LoginRoute) {
                            popUpTo(HomeRoute) { inclusive = true }
                        }
                    }
                }
            ) { innerPadding ->
                HomeScreen(Modifier.padding(innerPadding), homeViewModel)
            }
        }

        composable<LoginRoute> {
            Scaffold(Modifier.fillMaxSize()) { innerPadding ->
                LoginScreen(
                    Modifier.padding(innerPadding),
                    loginViewModel,
                    //delegacion del manejo de las acciones del login al viewModel
                    onAction = loginViewModel::onAction
                ) {
                    //navegacion a la pantalla principal
                    navController.navigate(HomeRoute) {
                        popUpTo(LoginRoute) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}
        /*caso argumentos obligatorios*/
        /*composable(Edit.route,
            *//*se establece el type con NavType para el argumento*//*
            arguments = listOf(
                navArgument("edad") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            ScaffoldWithDrawer(
                navigationController
            ) {
                EditScreen(
                    navigationController,
                    Modifier,
                    backStackEntry.arguments?.getInt("edad") ?: 0
                )
            }
        }*/

        /*caso argumentos opcionales*/
        /*composable(Add.route,
            *//*se establece un defaultValue para el argumento en lugar de su type*//*
            arguments = listOf(
                navArgument("name") { defaultValue = "Pedri" }
            )
        ) { backStackEntry ->
            ScaffoldWithDrawer (
                navigationController
            ) {
                AddScreen(navigationController, Modifier,
                    backStackEntry.arguments?.getString("name")
                )
            }
        }*/
