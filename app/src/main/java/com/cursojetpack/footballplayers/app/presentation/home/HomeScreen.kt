package com.cursojetpack.footballplayers.app.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursojetpack.footballplayers.app.domain.home.Player
import com.cursojetpack.footballplayers.app.presentation.home.structure.ExtendedPlayerCard
import com.cursojetpack.footballplayers.app.presentation.home.structure.PlayerCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier, homeViewModel: HomeViewModel) {

    LaunchedEffect(key1 = true) {
        homeViewModel.getPlayers()
    }

    val isExtendedCard by homeViewModel.isExtendedCard.observeAsState()

    val players by homeViewModel.players.collectAsState()

    val scrollState = rememberLazyListState()
    val showButton by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 0
        }
    }
    val coroutineScope = rememberCoroutineScope()
    val myPlayers: Map<String, List<Player>> = players.groupBy { it.position }

    /*Lo que se necesita para estructurar un lazyColumn con datos
* obtenidos de una base de datos es un modelo de datos, una funcion que
* setee los datos en funcion del modelo y retorne una lista del tipo de
* dato del modelo y una funcion que se encargue de darle el estilo a
* cada elemento de la lista*/
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            state = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {

            myPlayers.forEach { (position, players) ->

                stickyHeader {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 6.dp)
                    ) {
                        Text(
                            "Position: $position",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                items(items = players) { player ->

                    PlayerCard(player) {
                        homeViewModel.toggleExtendedCard(player)
                    }

                    if (isExtendedCard?.imageUri == player.imageUri) {
                        AnimatedVisibility(
                            visible = isExtendedCard?.imageUri == player.imageUri,
                            enter = slideInVertically(animationSpec = spring(dampingRatio = 0.9f)) +
                                    fadeIn(animationSpec = tween(200)),
                            exit = slideOutVertically() + fadeOut()
                        ) {
                            ExtendedPlayerCard(player) {
                                homeViewModel.toggleExtendedCard(player)
                            }
                        }
                    }
                }
            }
        }

        if (showButton) {
            TextButton(
                onClick = {
                    /*animateScrollToItem es una suspend function
                    * por lo cual debo de llamarla desde una corrutina*/
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text("Go Up")
            }
        }

    }
}


    //Se pueden anidar lazy rows dentro de lazycolumns y viceversa, pero no dos del mismo tipo

    /*LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(items = getPlayers()) { player ->
            PaintPlayer(player) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }*/

/*
@Composable
fun PaintPlayer(player: Player, onPlayerSelected: @Composable (String) -> Unit) {
    PlayerCard(Modifier, player, onPlayerSelected)
}*/
