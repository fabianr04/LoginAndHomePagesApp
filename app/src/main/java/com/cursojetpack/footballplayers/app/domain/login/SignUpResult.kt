package com.cursojetpack.footballplayers.app.domain.login


//definicion de la clase SignUpResult para declarar
//los posibles estados de un registro de usuario
sealed class SignUpResult {
    data class Success(val username: String): SignUpResult()
    data object Cancelled: SignUpResult()
    data class Failure(val error: String): SignUpResult()
}
