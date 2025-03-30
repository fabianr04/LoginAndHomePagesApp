package com.cursojetpack.footballplayers.app.presentation.login.components.texts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

//estructuracion de los cuadros de texto en caso
//de usar inicio de sesion con email y contraseña
@Composable
fun MyTextField(
    modifier: Modifier,
    placeholder: @Composable () -> Unit,
    value: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(4.dp),
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation
    )
}