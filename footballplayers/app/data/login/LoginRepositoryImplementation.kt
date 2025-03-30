package com.cursojetpack.footballplayers.app.data.login

import android.content.Context
import android.credentials.GetCredentialException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.ClearCredentialStateRequest.Companion.TYPE_CLEAR_CREDENTIAL_STATE
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import com.cursojetpack.footballplayers.R
import com.cursojetpack.footballplayers.app.domain.login.SignInResult
import com.cursojetpack.footballplayers.app.domain.login.SignUpResult
import com.cursojetpack.footballplayers.app.domain.home.UserData
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


/*las funciones que tenga el repositorio seran llamadas desde los casos de uso*/

/*el respositorio es donde se va a gestionar las peticiones
 de forma tal que este tenga una instancia del servicio en
 caso de necesitar ser llamado, o bien una instancia de la base de datos*/

/*en este deben de definirse funciones que llamen a funciones del
 servicio que a su vez llamen a funciones de la interfaz*/

/*o en caso de que haya base de datos llama al archivo con las funciones de la base de datos
  que llamara a las funciones de la interfaz que consulten en la base de datos*/



class LoginRepositoryImplementation @Inject constructor(
    private val auth: FirebaseAuth,
    private val credentialManager: CredentialManager,
    @ApplicationContext private val context: Context
) : LoginRepository {

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override suspend fun signInWithGoogle(): SignInResult {
        return try {

            //configuracion de la solicitud de credenciales de google
            val gso = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .build()

            //construccion de la solicitud de credenciales
            val signInRequest = GetCredentialRequest.Builder()
                .addCredentialOption(gso)
                .build()

            Log.d("GoogleSignIn", "Solicitando credenciales con CredentialManager")
            //obtencion de la credencial con credential manager
            val credentialResponse = credentialManager.getCredential(
                context = context,
                request = signInRequest
            )

            val credential = credentialResponse.credential
            Log.d("GoogleSignIn", "Credencial obtenida: ${credential::class.java.simpleName}")

            //manejo de la resuesta de la credencial
            handleSignInResult(credentialResponse)

            //verificacion de usuario autenticado
            val user = auth.currentUser
            Log.d("GoogleSignIn", "Usuario autenticado: ${user?.displayName}")
            SignInResult.Success("Usuario autenticado: ${user?.displayName}")

        } catch (e: GetCredentialCancellationException) {
            e.printStackTrace()
            SignInResult.Cancelled
        } catch (e: NoCredentialException) {
            e.printStackTrace()
            SignInResult.NoCredentials
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            SignInResult.Failure("Error al obtener credenciales.")
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()
            SignInResult.Failure("Error al autenticar en Firebase.")
        } catch (e: Exception) {
            e.printStackTrace()
            SignInResult.Failure("Error desconocido.")
        }
    }

    private suspend fun handleSignInResult(response: GetCredentialResponse) {
        // Obtencion de la credencial de Google
        val credential = response.credential
        Log.d("GoogleSignIn", "Credencial de Google obtenida: $credential")

        //creacion del id Token de google a partir de la credencial obtenida
        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
        Log.d("GoogleSignIn", "Token de Google: $googleIdTokenCredential")

        //obtencion del id token
        val idToken = googleIdTokenCredential.idToken

        //autenticacion con firebase
        firebaseAuthWithGoogle(idToken)
    }

    private suspend fun firebaseAuthWithGoogle(idToken: String) {
        //obtencion de la credencial de firebase en base al id token de google
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("FirebaseAuth", "Usuario autenticado correctamente")
                    Log.d("FirebaseAuth", "Nombre: ${user?.displayName}")
                    Log.d("FirebaseAuth", "Foto: ${user?.photoUrl}")
                } else {
                    Log.e(
                        "FirebaseAuth",
                        "Error en la autenticación con Firebase: ${task.exception?.message}"
                    )
                }
            }.await()
    }

    /*Log.d("GoogleSignIn", "Respuesta de CredentialManager: ${credentialResponse.credential}")

    val googleCredential = credentialResponse.credential as? GoogleIdTokenCredential
        ?: return SignInResult.Failure("No se encontraron credenciales")

    Log.d("GoogleSignIn", "Credencial de Google obtenida: $googleCredential")

    val googleIdToken = googleCredential.idToken

    if (googleIdToken.isEmpty()) {
        return SignInResult.Failure("Token de Google inválido")
    }

    Log.d("GoogleSignIn", "Token de Google: $googleIdToken")


    val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

    Log.d("GoogleSignIn", "Credencial de Firebase creada")

    Log.d("GoogleSignIn", "Autenticando con Firebase")

    val firebaseAuthResult =
        auth.signInWithCredential(firebaseCredential).await()

    val firebaseUser = firebaseAuthResult.user
        ?: return SignInResult.Failure("Error al iniciar sesión en Firebase.")

    Log.d("GoogleSignIn", "Usuario autenticado: ${firebaseUser.uid}")
    Log.d("GoogleSignIn", "Nombre: ${firebaseUser.displayName}")
    Log.d("GoogleSignIn", "Foto: ${firebaseUser.photoUrl}")

    SignInResult.Success(firebaseUser.displayName ?: "No se encontro el nombre de usuario")*/
    /*try {
        // Construcción de la solicitud de credenciales de Google
        val signInRequest = GetCredentialRequest.Builder()
            .addCredentialOption(GetGoogleIdOption.Builder().build())
            .build()

        // Obtener la credencial con CredentialManager
        val result = credentialManager.getCredential(context, signInRequest)
        val googleCredential = result.credential as? PublicKeyCredential
            ?: return SignInResult.Failure("No se pudo obtener la credencial de Google.")

        // Extraer el token de ID de Google
        val googleIdToken = googleCredential.authenticationResponseJson

        // Crear credencial de Firebase
        val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

        // Iniciar sesión en Firebase
        val authResult = auth.signInWithCredential(firebaseCredential).await()
        val firebaseUser = authResult.user
            ?: return SignInResult.Failure("No se pudo autenticar con Firebase.")

        // Crear objeto UserData
        val userData = UserData(
            uid = firebaseUser.uid,
            name = firebaseUser.displayName ?: "Usuario",
            email = firebaseUser.email ?: ""
        )

        // Devolver resultado exitoso
        SignInResult.Success(userData.name!!)
    } catch (e: GetCredentialCancellationException) {
        SignInResult.Cancelled
    } catch (e: NoCredentialException) {
        SignInResult.NoCredentials
    } catch (e: GetCredentialException) {
        SignInResult.Failure(e.message ?: "Error al obtener credenciales.")
    } catch (e: FirebaseFirestoreException) {
        SignInResult.Failure("Error al guardar en Firestore: ${e.message}")
    } catch (e: Exception) {
        SignInResult.Failure(e.message ?: "Error desconocido.")
    }*/


    /*    override suspend fun signUp(email: String, password: String): SignUpResult {
        return try {
            // Crear las credenciales usando CredentialManager
            credentialManager.createCredential(
                context = context,
                request = CreatePasswordRequest(
                    id = email,
                    password = password
                )
            )

            // Registrar usuario en Firebase Auth
            val firebaseAuthResult =
                auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = firebaseAuthResult.user
                ?: return SignUpResult.Failure("Error al crear el usuario en Firebase.")

            // Si la creación es exitosa, retorna el nombre de usuario
            SignUpResult.Success(firebaseUser.displayName ?: email)
        } catch (e: CreateCredentialCancellationException) {
            e.printStackTrace()
            SignUpResult.Cancelled
        } catch (e: CreateCredentialException) {
            e.printStackTrace()
            SignUpResult.Failure("Error al crear las credenciales.")
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()
            SignUpResult.Failure("Error al registrar en Firebase.")
        } catch (e: Exception) {
            e.printStackTrace()
            SignUpResult.Failure("Error desconocido.")
        }
    }*/

    // Validar campos de entrada
    /*try {
        if (email.isBlank() || password.isBlank()) {
            return SignUpResult.Failure("Todos los campos son obligatorios.")
        }

        // Crear usuario en Firebase Auth
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val firebaseUser = result.user
            ?: return SignUpResult.Failure("No se pudo crear el usuario.")

        // Actualizar el perfil del usuario con el nombre
        firebaseUser.updateProfile(userProfileChangeRequest { displayName = name }).await()

        // Crear objeto UserData (sin contraseña)
        val userData = UserData(
            uid = firebaseUser.uid,
            name = name,
            email = firebaseUser.email ?: ""
        )

        // Guardar en Firestore
        val userDoc = firestore.collection("users").document(firebaseUser.uid).get().await()
        if (!userDoc.exists()) {
            // Guardar usuario en Firestore solo si es nuevo
            firestore.collection("users").document(userData.uid).set(userData).await()
        }

        credentialManager.createCredential(
            context = context, request = CreatePasswordRequest(
                id = name,
                password = password
            )
        )
        // Devolver resultado exitoso
        SignUpResult.Success(userData.name!!)
    } catch (e: FirebaseAuthWeakPasswordException) {
        SignUpResult.Failure("La contraseña es demasiado débil.")
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        SignUpResult.Failure("El correo electrónico no es válido.")
    } catch (e: FirebaseAuthUserCollisionException) {
        SignUpResult.Failure("El correo electrónico ya está en uso.")
    } catch (e: Exception) {
        SignUpResult.Failure(e.message ?: "Error al registrar usuario.")
    }*/

    override suspend fun signOut(): SignInResult {
        return try {
            //cierre de sesion con firebase
            auth.signOut()
            Log.d("LoginRepository", "Usuario ha cerrado sesión.")
            //limpieza de credenciales con credential manager
            credentialManager.clearCredentialState(
                ClearCredentialStateRequest(
                    TYPE_CLEAR_CREDENTIAL_STATE
                )
            )
            Log.d("LoginRepository", "Credenciales limpiadas.")
            SignInResult.Failure("")
        } catch (e: Exception) {
            SignInResult.Failure(e.message ?: "Error al cerrar sesión.")
        }
    }

    override suspend fun getCurrentUser(): UserData? {
        return try {
            //obtencion del usuario autenticado con firebase
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                Log.d(
                    "LoginRepository",
                    "Usuario encontrado: ${firebaseUser.displayName}, Foto: ${firebaseUser.photoUrl}"
                )
                //retorno de un objeto UserData con los datos del usuario
                UserData(
                    uid = firebaseUser.uid,
                    name = firebaseUser.displayName ?: "Sin nombre",
                    userPhoto = firebaseUser.photoUrl
                )
            } else {
                Log.e("LoginRepository", "No se encontró usuario en FirebaseAuth")
                null
            }
        } catch (e: FirebaseAuthException) {
            Log.e("LoginRepository", "Error al obtener el usuario actual", e)
            null
        }
    }
}