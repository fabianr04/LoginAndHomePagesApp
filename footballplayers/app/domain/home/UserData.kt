package com.cursojetpack.footballplayers.app.domain.home

import android.net.Uri

//definicion de la clase UserData
data class UserData(
    val uid: String,
    val name: String?,
    val userPhoto: Uri? = null
)