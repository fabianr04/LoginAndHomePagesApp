package com.cursojetpack.footballplayers.app.presentation.login.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cursojetpack.footballplayers.app.domain.login.LoginActions
import com.cursojetpack.footballplayers.app.presentation.login.LoginViewModel
import com.cursojetpack.footballplayers.app.presentation.login.components.buttons.LoginButton
import com.cursojetpack.footballplayers.app.presentation.login.components.texts.MyTitle

//estructuracion del contenido de la pantalla de login
@Composable
fun Content(
    modifier: Modifier,
    loginViewModel: LoginViewModel
    //en caso de manejar varias acciones con la clase LoginActions
    /*onAction: (LoginActions) -> Unit*/
) {

    Column(
        modifier
            .fillMaxSize(1f)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//      val isEnabled: Boolean by loginViewModel.isEnableLogin.observeAsState(initial = false)
        MyTitle(Modifier, "Football Players")

        Spacer(modifier = Modifier.height(24.dp))

        LoginButton() {
            loginViewModel.signInWithGoogle()

            /*if (formState.isRegistering) {
                loginViewModel.signUp()
            } else {
                loginViewModel.signInWithGoogle()
            }*/
        }
    }
}

//Para un LoginScreen con email y contraseña

/*
      var passwordVisible by remember { mutableStateOf(false) }

        val visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
        MyTextField(
            Modifier,
            {
                Text("Email", fontSize = 14.sp)
            },
            formState.email,
        ) {
            onAction(OnEmailChange(it))
        }

        Spacer(modifier = Modifier.height(8.dp))

        MyTextField(
            Modifier,
            {
                Text("Password", fontSize = 14.sp)
            },
            formState.password,
            visualTransformation = visualTransformation,
            trailingIcon = {
                val image = if (passwordVisible) {
                    R.drawable.visibility_off_24px
                } else {
                    R.drawable.visibility_24px
                }
                VisibilityButton(image, passwordVisible) {
                    passwordVisible = !passwordVisible
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        ) {
            onAction(OnPasswordChange(it))
        }

        Spacer(modifier = Modifier.height(2.dp))

        val color = animateColorAsState(
            targetValue = if (formState.isValid) Color.Green else Color.Red,
            animationSpec = tween(durationMillis = 500)
        )

        val realColor = color.value

        if (formState.isRegistering){
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Password must be at least 6 characters",
                    color = realColor,
                    fontSize = 12.sp
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    "Password must contain at least one uppercase letter",
                    color = realColor,
                    fontSize = 12.sp
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    "Password must contain at least one number",
                    color = realColor,
                    fontSize = 12.sp
                )
            }
            if (formState.errorMessage == "Datos inválidos") {
                MyDialog(formState.showDialog) {
                    onAction(LoginActions.OnDismissDialog)
                }
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(if (formState.isRegistering) "Sign In" else "Register", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Switch(
                checked = formState.isRegistering,
                onCheckedChange = {
                    onAction(OnToggleIsRegister)
                }
            )
            Spacer(Modifier.weight(1f))
            ForgotPasswordText(Modifier)
        }
*/
