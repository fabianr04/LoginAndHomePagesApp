package com.cursojetpack.footballplayers.app.data.home

import android.net.Uri
import com.cursojetpack.footballplayers.app.domain.home.Player

interface HomeRepository {
    suspend fun getPlayers(): List<Player>
    suspend fun getImage(imageFileName: String): Uri?
}