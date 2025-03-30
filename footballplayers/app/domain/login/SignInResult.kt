package com.cursojetpack.footballplayers.app.domain.login


//definicion de la clase SignInResult para declarar los posibles
//estados del inicio de sesion
sealed class SignInResult {
    data class Success(val username: String): SignInResult()
    data object Cancelled: SignInResult()
    data class Failure(val error: String): SignInResult()
    data object NoCredentials: SignInResult()
    data object FirebaseAuthFailure: SignInResult()
    data object NullFirebaseCredential: SignInResult()
}