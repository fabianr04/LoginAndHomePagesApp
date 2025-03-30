package com.cursojetpack.footballplayers.app.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.cursojetpack.footballplayers.app.domain.login.LoginActions
import com.cursojetpack.footballplayers.app.domain.login.SignInResult
import com.cursojetpack.footballplayers.app.presentation.login.layouts.Content
import com.cursojetpack.footballplayers.app.presentation.login.layouts.Footer

@Composable
fun LoginScreen(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    onAction: (LoginActions) -> Unit,
    onLoggedIn: () -> Unit
) {
    val signInResult: SignInResult by loginViewModel.signInResult.collectAsState()
    val isUserLoggedIn: Boolean by loginViewModel.isUserLoggedIn.collectAsState()

    LaunchedEffect(key1 = signInResult) {
        onAction(LoginActions.OnSignIn(signInResult))
    }
    LaunchedEffect(key1 = isUserLoggedIn) {
        loginViewModel.loadUserProfile()
        if (isUserLoggedIn) {
            onLoggedIn()
        }
    }

    Column(modifier.fillMaxSize()) {
//        Header(modifier)
        Content(
            modifier = Modifier.weight(1f),
            loginViewModel = loginViewModel/*,
            onAction = onAction*/
        )
        Footer(modifier)
    }
}