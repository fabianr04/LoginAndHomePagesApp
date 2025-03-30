package com.cursojetpack.footballplayers.app.presentation.login.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Footer (modifier: Modifier) {
    Row (
        modifier.fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
        ) {
        Text("Developed by: Fabian Rodriguez")

        /*Spacer(Modifier.width(4.dp))
        Box(
            Modifier.clip(RoundedCornerShape(10.dp))
            .clickable {  }
        ) {
            Text(
                "Sign up.",
                color = MaterialTheme.colorScheme.primary
            )
        }*/
    }
}