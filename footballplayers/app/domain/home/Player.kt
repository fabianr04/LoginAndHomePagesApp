package com.cursojetpack.footballplayers.app.domain.home

import android.net.Uri

//definicion de la clase Player
data class Player(
    val name: String = "",
    val country: String = "",
    val team: String = "",
    val number: Int = 0,
    val position: String = "",
    val imageFileName: String = "",
    var imageUri: Uri? = Uri.EMPTY,
    val goals: Int = 0,
    val assists: Int = 0,
    val games: Int = 0,
    val yellowCards: Int = 0
)
