package com.cursojetpack.footballplayers

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

//declaracion de la aplicacion para la inyeccion de dependencias con hilt
@HiltAndroidApp
class FootballPlayersApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}