package com.cursojetpack.footballplayers.app.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursojetpack.footballplayers.app.data.home.HomeRepository
import com.cursojetpack.footballplayers.app.domain.home.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    //estado para los datos de los jugadores que se actualiza desde el repositorio
    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    //estado para mostrar u ocultar la tarjeta extendida
    private var _isExtendedCard = MutableLiveData<Player?>(null)
    val isExtendedCard: LiveData<Player?> = _isExtendedCard

    //manejo del estado de la tarjeta extendida
    fun toggleExtendedCard(player: Player?) {
        _isExtendedCard.value =
            if (_isExtendedCard.value?.imageUri == player?.imageUri) null else player
    }

    //obtencion de los datos desde la base de datos y asignacion al estado
    fun getPlayers(){
        viewModelScope.launch {
            Log.d("HomeViewModel", "Solicitando jugadores desde HomeRepository...")
            val playersWithOutImages = homeRepository.getPlayers()
            val playersWithImages = playersWithOutImages.map { player ->
                player.copy(imageUri = homeRepository.getImage(player.imageFileName))
            }
            Log.d("HomeViewModel", "Jugadores con imágenes: $playersWithImages")
            _players.value = playersWithImages
            Log.d("HomeViewModel", "Jugadores obtenidos: ${_players.value}")
        }
    }

}