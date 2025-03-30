package com.cursojetpack.footballplayers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.cursojetpack.footballplayers.app.presentation.home.HomeViewModel
import com.cursojetpack.footballplayers.app.presentation.login.LoginViewModel
import com.cursojetpack.footballplayers.app.presentation.navigation.MyNavHost
import com.cursojetpack.footballplayers.ui.theme.LazyColumnTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnTheme {
                val navController = rememberNavController()
                val loginViewModel = viewModel<LoginViewModel>()
                val homeViewModel = viewModel<HomeViewModel>()

                MyNavHost(
                        navController,
                        loginViewModel,
                        homeViewModel = homeViewModel
                )
            }
        }
    }
}












