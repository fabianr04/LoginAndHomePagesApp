package com.cursojetpack.footballplayers.app.domain.login

//definicion de la clase LoginFormState con los posibles valores
//que pueda manejar la pantalla de Login
data class LoginFormState(
    val username: String? = null,
    val errorMessage: String? = null /*,
    val email: String = "",
    val password: String = "",
    val isValid: Boolean = false,
    val showDialog: Boolean = false,
    val isRegistering: Boolean = false*/
)

