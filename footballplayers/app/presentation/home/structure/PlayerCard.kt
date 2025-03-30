package com.cursojetpack.footballplayers.app.presentation.home.structure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cursojetpack.footballplayers.app.domain.home.Player

@Composable
fun PlayerCard(player: Player, onToggleIsExtended: (Player) -> Unit){
    Card(
        modifier = Modifier
            .padding(12.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onToggleIsExtended(player) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 14.dp
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = player.imageUri,
                contentDescription = "Player Photo",
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.height(10.dp))
            Column(
                Modifier.padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
            ){
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "Nombre: ${player.name}", fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "Team: ${player.team}", fontWeight = FontWeight.SemiBold)
                }

                Spacer(Modifier.height(8.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "Numero: ${player.number}", fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.width(10.dp))
                    Text(text = "Position: ${player.position}", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}