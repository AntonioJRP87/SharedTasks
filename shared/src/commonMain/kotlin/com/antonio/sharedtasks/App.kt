package com.antonio.sharedtasks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antonio.sharedtasks.data.readWelcomeMessage
import com.antonio.sharedtasks.data.saveAndReadSmoke
import com.antonio.sharedtasks.data.signInAnonymously
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

import sharedtasks.shared.generated.resources.Res
import sharedtasks.shared.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var showText by remember { mutableStateOf("Sin sesión") }
        var dates by remember { mutableStateOf("Sin datos") }
        var remoteMessage by remember { mutableStateOf( "Sin Datos de Firebase") }

        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { scope.launch {
                try {
                    showText = signInAnonymously()
                } catch (e: Exception) {
                    showText = e.message ?: "Error desconocido"
                }
            } }) {
                Text("Click me!")
            }
            Text(showText)
            Button(onClick = { scope.launch {
                try {
                    dates = saveAndReadSmoke()
                } catch (e: Exception) {
                    showText = e.message ?: "Error desconocido"
                }
            } }) {
                Text("Probar Firestore")
            }
            Text(dates)
            Button(onClick = { scope.launch {
                try {
                    remoteMessage = readWelcomeMessage()
                } catch (e: Exception) {
                    remoteMessage = e.message ?: "Error desconocido"
                }
            } }) {
                Text("Probar Remote Config")
            }
            Text(remoteMessage)

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}