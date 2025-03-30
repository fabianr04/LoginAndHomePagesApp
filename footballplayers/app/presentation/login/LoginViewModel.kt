package com.cursojetpack.footballplayers.app.presentation.login

import android.net.Uri
import android.util.Log
import android.util.Patterns
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkAnnotation.Url
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursojetpack.footballplayers.app.data.login.LoginRepository
import com.cursojetpack.footballplayers.app.domain.login.LoginActions
import com.cursojetpack.footballplayers.app.domain.login.LoginFormState
import com.cursojetpack.footballplayers.app.domain.login.SignInResult
import com.cursojetpack.footballplayers.app.domain.login.SignUpResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    //en caso de manejar el estado de un boton de inicio de sesion con email y contraseña
    /*private fun enableButton(email: String, password: String): Boolean {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6)
    }*/

    //estado de carga de datos del usuario
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //estado de inicio de sesion con google
    private val _signInResult = MutableStateFlow<SignInResult>(SignInResult.Failure(""))
    val signInResult: StateFlow<SignInResult> = _signInResult.asStateFlow()

//    private val _signUpState = MutableStateFlow<SignUpResult>(SignUpResult.Failure(""))

    //estado de manejo de la pantalla de login
    private val _formState = MutableStateFlow(LoginFormState(username = null))
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

    //estado del nombre de usuario autenticado
    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> get() = _userName

    //estado de la foto del usuario autenticado
    private val _userPhoto = MutableLiveData<Uri?>()
    val userPhoto: LiveData<Uri?> get() = _userPhoto

    //estado de inicio de sesion del usuario
    private val _isUserLoggedIn = MutableStateFlow(true)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    //Carga de los datos del usuario autenticado
    fun loadUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val userData = loginRepository.getCurrentUser()
                _userName.value = userData?.name ?: "No user found"
                _userPhoto.value = userData?.userPhoto
                Log.d("LoginViewModel", "Datos del usuario: $userData")
                _isUserLoggedIn.value = userData != null
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error al cargar el perfil del usuario", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    //inicio de sesion con google
    fun signInWithGoogle() {
        viewModelScope.launch {
            val state = loginRepository.signInWithGoogle()
            _signInResult.value = state
            if (state is SignInResult.Success) {
                _formState.value = _formState.value.copy(username = state.username)
            }
        }
    }

    /*private fun isValidPassword(password: String): Boolean {
        if (password.length < 6) {
            return false
        }
        val hasUppercase = password.any { it.isUpperCase() }
        val hasNumber = password.any { it.isDigit() }
        return hasUppercase && hasNumber
    }*/

    //manejo de las acciones del usuario en la pantalla de login
    fun onAction(action: LoginActions) {
        when (action) {
            is LoginActions.OnSignIn -> {
                when (action.result) {
                    is SignInResult.Cancelled -> {
                        _formState.value =
                            _formState.value.copy(errorMessage = "Inicio de sesión cancelado")
                    }

                    is SignInResult.Failure -> {
                        _formState.value =
                            _formState.value.copy(
                                username = null,
                                errorMessage = "Inicio de sesión fallido"
                            )
                    }

                    is SignInResult.FirebaseAuthFailure -> {
                        _formState.value =
                            _formState.value.copy(errorMessage = "Error de autenticación de Firebase")
                    }

                    is SignInResult.NoCredentials -> {
                        _formState.value =
                            _formState.value.copy(errorMessage = "No se encontraron credenciales")
                    }

                    is SignInResult.NullFirebaseCredential -> {
                        _formState.value =
                            _formState.value.copy(errorMessage = "Credencial de Firebase nula")
                    }

                    is SignInResult.Success -> {
                        _formState.value = _formState.value.copy(username = action.result.username)
                        _isUserLoggedIn.value = true
                    }
                }
            }
                //For sign in with email and password
                /*is LoginActions.OnSignUp -> {
                when (action.result) {
                    is SignUpResult.Cancelled -> {
                        _formState.value =
                            _formState.value.copy(errorMessage = "Registro cancelado")
                    }

                    is SignUpResult.Failure -> {
                        _formState.value =
                            _formState.value.copy(errorMessage = "Registro de sesion fallido")
                    }

                    is SignUpResult.Success -> {
                        _formState.value = _formState.value.copy(username = action.result.username)
                    }
                }
            }

            is LoginActions.OnPasswordChange -> {
                if (_formState.value.isRegistering) {
                    _formState.value = _formState.value.copy(password = action.password)
                    _formState.value =
                        _formState.value.copy(isValid = isValidPassword(action.password))
                } else {
                    _formState.value = _formState.value.copy(password = action.password)
                    _formState.value = _formState.value.copy(isValid = action.password.isNotEmpty())
                }
//                _isEnableLogin.value = enableButton(_formState.value.email, action.password)
            }

            is LoginActions.OnToggleIsRegister -> {
                _formState.value =
                    _formState.value.copy(
                        email = "",
                        password = "",
                        isRegistering = !_formState.value.isRegistering
                    )
            }

            is LoginActions.OnEmailChange -> {
                _formState.value = _formState.value.copy(email = action.email)
                *//*if (!_formState.value.isRegistering) {
                     _isEnableLogin.value = enableButton(action.email, _formState.value.password)
                }
            }
            is LoginActions.OnDismissDialog -> {
                _formState.value = _formState.value.copy(showDialog = false)
            }*/
        }
    }

    //cierre de sesion
    fun signOut(onSignOutSuccess: () -> Unit) {
        viewModelScope.launch {
            val state = loginRepository.signOut()
            _signInResult.value = state
            _isUserLoggedIn.value = false
            if (state is SignInResult.Failure) {
                _formState.value =
                    _formState.value.copy(
                        username = null,
                        errorMessage = "No hay sesion iniciada"
                    )
                onSignOutSuccess()
            }
        }
    }
}
//For register users with email and password
/*fun signUp() {
    viewModelScope.launch {
        if (
            Patterns.EMAIL_ADDRESS.matcher(_formState.value.email).matches()
            && _formState.value.isValid
        ) {
            val state = loginRepository.signUp(
                _formState.value.email,
                _formState.value.password
            )
            _signUpState.value = state
            _formState.value = _formState.value.copy(
                email = "",
                password = "",
                isValid = false,
                isRegistering = false
            )
        } else {
            _signUpState.value = SignUpResult.Failure("Datos inválidos")
            _formState.value = _formState.value.copy(errorMessage ="Datos inválidos")
            _formState.value = _formState.value.copy(showDialog = true)
        }
    }
}*/
