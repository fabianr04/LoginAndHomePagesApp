package com.cursojetpack.footballplayers.app.data.login

import com.cursojetpack.footballplayers.app.domain.home.UserData
import com.cursojetpack.footballplayers.app.domain.login.SignInResult

/*las funciones del repositorio son llamadas desde el repositorio*/

/*el servicio es el que va a conectar realmente al servidor,
  teniendo funciones que internamente adopten un comportamiento
  definido de la interfaz, indicandole el contexto en que se va
   a ejecutar y que gestione la respuesta*/


interface LoginRepository {
    suspend fun signInWithGoogle(): SignInResult
    suspend fun signOut(): SignInResult
    suspend fun getCurrentUser(): UserData?
}