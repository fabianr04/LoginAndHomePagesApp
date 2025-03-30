package com.cursojetpack.footballplayers.app.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.cursojetpack.footballplayers.R
import com.cursojetpack.footballplayers.app.data.home.HomeRepository
import com.cursojetpack.footballplayers.app.data.home.HomeRepositoryImpl
import com.cursojetpack.footballplayers.app.data.login.LoginRepository
import com.cursojetpack.footballplayers.app.data.login.LoginRepositoryImplementation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import javax.inject.Singleton

/* para trabajar con un provider se debe de crear un modulo el cual
   si va a estar capacitado para ser inyectable con hilt
   para ello se crea una clase con la anotacion @Module
   se le especifica el alcance del modulo con @InstallIn
   y se crean los metodos que se van a inyectar con la anotacion
   @Singleton (en caso de querer que se instancie una sola vez) y @Provides
   para que sea inyectable con hilt
*/

//modulo para la inyeccion de dependencias de la capa de datos
//que no tienen soporte de inyeccion de dependencias con hilt
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager =
        CredentialManager.create(context)

    @Provides
    fun provideSupabaseClient(): SupabaseClient =
        createSupabaseClient(
            "https://slcjzzalltylkejbnbft.supabase.co", R.string.supabase_key.toString()
        ) {
            install(Storage)
        }

    @Provides
    fun provideLoginRepository(
        auth: FirebaseAuth,
        credentialManager: CredentialManager,
        @ApplicationContext context: Context
    ): LoginRepository = LoginRepositoryImplementation(auth, credentialManager, context)

    @Provides
    fun provideHomeRepository(
        firestore: FirebaseFirestore,
        supabaseClient: SupabaseClient
    ): HomeRepository = HomeRepositoryImpl(firestore, supabaseClient)


}


    /*@Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    *//*para inyectar una interfaz no se hacen anotaciones en el propio archivo de la interfaz
    * sino que se emplean los provides y se crea una funcion que retorne la interfaz*//*
    @Singleton
    @Provides
    fun provideLoginClient(retrofit: Retrofit): LoginClient {
        return retrofit.create(LoginClient::class.java)
    }*/
