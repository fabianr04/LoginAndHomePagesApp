package com.cursojetpack.footballplayers.app.data.home

import android.net.Uri
import android.util.Log
import com.cursojetpack.footballplayers.app.domain.home.Player
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val supabaseClient: SupabaseClient
) : HomeRepository {

    override suspend fun getPlayers(): List<Player> {
        return try {
            //indicar la coleccion sobre la cual se va a operar
            val ref = firestore.collection("players")
            //obtener la informacion de la coleccion
            val querySnapshot = ref.get().await()
            Log.d(
                "HomeRepositoryImpl",
                "Número de documentos en 'players': ${querySnapshot.size()}"
            )

            if (querySnapshot.isEmpty) {
                Log.e("HomeRepositoryImpl", "Firestore no devolvió documentos.")
                return emptyList()
            }
            //mappear los elementos obtenidos en la query para volverlos de tipo Player
            val players = querySnapshot.documents.map { document ->
                Log.d("HomeRepositoryImpl", "document: ${document.id}")
                Log.d("HomeRepositoryImpl", "document: ${document.data}")
                document.toObject<Player>() ?: Player(
                    name = "",
                    country = "",
                    team = "",
                    number = 99,
                    position = "",
                    imageFileName = "",
                    imageUri = Uri.EMPTY,
                    goals = 0,
                    assists = 0,
                    games = 0,
                    yellowCards = 0
                )
            }
            Log.d("HomeRepositoryImpl", "Players: $players")
            //retorno de la lista de jugadores configurados
            players
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getImage(imageFileName: String): Uri? {
        return try {
            // Acceso al servicio de almacenamiento en Supabase
            val storage = supabaseClient.storage.from("players-photos")

            // Obtencion de la URL pública de la imagen almacenada
            val publicUrlResponse = storage.publicUrl(imageFileName)
            Log.d("HomeRepositortyImpl", "Public URL: $publicUrlResponse")
            // Retorno de la URI pública
            Uri.parse(publicUrlResponse)
        } catch (e: Exception) {
            Log.e("ImageRepository", "Error al obtener la imagen desde Supabase: ${e.message}")
            null
        }
    }

}