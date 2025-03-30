package com.cursojetpack.footballplayers.app.presentation.home.structure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cursojetpack.footballplayers.app.domain.home.Player

//estructuracion de la tarjeta de jugador que se mostrará
//al clickear sobre las tarjetas principales
@Composable
fun ExtendedPlayerCard(player: Player, onToggleIsExtended: (Player) -> Unit) {

    Card(
        modifier = Modifier
            .padding(12.dp)
            .width(450.dp)
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
        Column(
            Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = player.imageUri,
                contentDescription = "Player Photo",
                contentScale = ContentScale.Fit
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Text(text = "Nombre: ${player.name}", fontWeight = FontWeight.SemiBold)
                Text(text = "Nacionalidad: ${player.country}", fontWeight = FontWeight.SemiBold)

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "Numero: ${player.number}", fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.weight(1f))
                    Text(text = "Position: ${player.position}", fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.width(33.dp))
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp)) {
                        Text(text = "Goals: ${player.goals}", fontWeight = FontWeight.SemiBold)
                        Text(text = "Assists: ${player.assists}", fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(Modifier.weight(1f))
                    Column(Modifier.padding(end = 10.dp)) {
                        Text(text = "Games: ${player.games}", fontWeight = FontWeight.SemiBold)
                        Text(text = "Yellow Cards: ${player.yellowCards}", fontWeight = FontWeight.SemiBold)
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Team: ${player.team}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}