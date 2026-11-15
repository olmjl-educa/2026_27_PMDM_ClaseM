package com.example.scaffolddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.scaffolddemo.ui.theme.ProyectoUT2ClaseTheme



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoUT2ClaseTheme {
                MiScaffold()

            } // Fin del theme
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    ProyectoUT2ClaseTheme {
        MiScaffold()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MiScaffold() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Mi aplicación") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.White
                )
            )
        }, // Fin llave del topBar
        floatingActionButton = {
            FloatingActionButton(onClick = { println("Hola") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Añadir"
                )
            }
        }
    ) { innerPadding ->
        Text(
            text = "Contenido de la pantalla",
            modifier = Modifier.padding(innerPadding)
        )

    }
}