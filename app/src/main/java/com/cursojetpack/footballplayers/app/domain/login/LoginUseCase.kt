package com.cursojetpack.footballplayers.app.domain.login

import com.cursojetpack.footballplayers.app.data.login.LoginRepository
import javax.inject.Inject

/*los casos de uso son los que se llaman desde el viewmodel*/

/*los casos de uso se emplean para separar acciones que puedan ser llamadas
  desde diferentes partes de la aplicación*/

/*estas acciones podran ser llamadas para realizar operaciones de negocio,
  bien sea ejecutandose 'y ya' o para combinarse con otros casos de uso*/

/*en este caso la logica del caso de uso del login es simple porque el correo y contrasena
  no estan siendo verificados. pero si hubiera que verificar en una base de datos la existencia
  del usuario y contrasena, cada uno tendria su propio caso de uso*/

/*si no se usa inyeccion de dependencias, se debe crear una instancia del repositorio
  de forma manual*/

//definicion de un caso de uso para aplicar la logica del repositoryImpl
class LoginUseCase @Inject constructor(
//    con inyeccion de dependencias
    private val repository: LoginRepository
) {

    /*el uso de operator e invoke permite que al instanciar el caso de uso en el viewModel
      se acceda directamente a la funcion que contiene el caso de uso, sin necesidad de crear
      una funcion aparte*/
    suspend operator fun invoke(): SignInResult {
        return repository.signInWithGoogle()
    }

}