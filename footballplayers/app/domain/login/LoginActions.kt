package com.cursojetpack.footballplayers.app.domain.login

import com.cursojetpack.footballplayers.app.data.Routes

//definicion de la clase LoginActions con los posibles
//comportamientos de la pantalla de login
sealed interface LoginActions {
    data class OnSignIn(val result: SignInResult): LoginActions
}
    //for sign in with email and password
    /*data class OnSignUp(val result: SignUpResult): LoginActions
    data class OnEmailChange(val email: String): LoginActions
    data class OnPasswordChange(val password: String): LoginActions
    data object OnToggleIsRegister: LoginActions
    data object OnDismissDialog: LoginActions*/
