package com.example.traintrack.Registro

import android.annotation.SuppressLint
import android.media.Image
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.traintrack.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception

/**
 * Esta función representa la vista principal del registro.
 * Es la primera pantalla que se muestra al usuario.
 */
@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavController,viewModel: LoginScreenViewModel) {
    val context = LocalContext.current
    val token = "717556830084-88psc6uq5oc9l7vcs95od7vh3vo10i3s.apps.googleusercontent.com"
    // Se utiliza rememberLauncherForActivityResult para obtener el resultado de la actividad
    val launcher = rememberLauncherForActivityResult(

        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            // Iniciar sesión con las credenciales de Google y navegar a la página de inicio

            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signInWithGoogleCredential(credential) {
            navController.navigate("Homepage")
            }
        } catch (ex: Exception) {
            Log.d("TrainTracker", "GoogleSignIn falla")
            // Manejo de la excepción en caso de falla en GoogleSignIn

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TrainTracker",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de TrainTracker",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(150.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    val opciones = GoogleSignInOptions
                        .Builder(
                            GoogleSignInOptions.DEFAULT_SIGN_IN
                        )
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                    val googleSignCliente = GoogleSignIn.getClient(context, opciones)
                    // Iniciar el flujo de inicio de sesión con Google

                    launcher.launch(googleSignCliente.signInIntent)
                }
                .background(Color.Blue),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Login con Google",
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)
            )

        }
    }
}




