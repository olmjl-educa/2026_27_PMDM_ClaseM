package com.example.contenedores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contenedores.ui.theme.Unidad2Theme

class MainActivityContenedores : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unidad2Theme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    MiComposable(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Prueba de commit

@Composable
fun MiComposable(modifier: Modifier = Modifier) {
    // [MODIFICADO] En cada EJEMPLO, el modifier recibido como parámetro se aplica
    // solo al contenedor raíz.

    // EJEMPLO 1. Columna con 3 hijos, con weight (peso) definido. El contenedor raíz
    // ocupa toda la pantalla (fillMaxSize()) y reparte el espacio entre los
    // hijos según su peso.
    // [MODIFICADO] Se ha añadido un padding horizontal de 16.dp al contenedor raíz,
    // ya que el innerPadding recibido del Scaffold ya se aplica automáticamente a la
    // parte superior e inferior de la pantalla.
//    Column(modifier = modifier.fillMaxSize().padding(horizontal = 16.dp), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(
//            text = "Hello",
//            modifier = Modifier.background(Color.Blue).fillMaxSize().weight(100f)
//        )
//        Text(
//            text = "Hello",
//            modifier = Modifier.background(Color.Yellow).fillMaxSize().weight(200f)
//        )
//        Text(
//            text = "Hello",
//            modifier = Modifier.background(MaterialTheme.colorScheme.secondary).fillMaxSize().weight(100f)
//        )
//    }

    // EJEMPLO 2. Fila con 3 hijos, SIN weight (peso) definido. El contenedor raíz (Row)
    // ocupa toda la pantalla (fillMaxSize()).
    // NOTA: sin weight definido, los hijos (los 3 Text) ocupan el espacio que necesiten.
    // El espaciado entre ellos lo definimos con horizontalArrangement.
//    Row(
//        modifier = Modifier
//            .fillMaxSize(),
//            //.padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
//    ) {
//        Text(
//            text = "Hello",
//            modifier = Modifier.background(Color.Blue)
//        )
//
//        Text(
//            text = "Hello",
//            modifier = Modifier.background(Color.Yellow)
//        )
//
//        Text(
//            text = "Texto más largo",
//            modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
//        )
//    }

    // EJEMPLO 3. Fila con 30 hijos, con scroll horizontal.
    Row(
        modifier = modifier   // Aquí daría igual usar modifier.horizontalScroll o
                              // Modifier.horizontalScroll.
            //.fillMaxSize()
            .horizontalScroll(rememberScrollState())
    ) {
        repeat(30) { i ->
            Text(
                text = "elemento $i",
                // [MODIFICADO] Cada hijo parte de Modifier para no repetir el padding recibido.
                //modifier = Modifier.padding(8.dp)
                // NOTA: Ver diferencia entre poner Modifier.padding y modifier.padding
                modifier = modifier.padding(8.dp)
            )
        }
    }
} // Fin de la función MiComposable

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MiComposablePreview() {
//    Unidad2Theme {
//        MiComposable()
//    }
    // [MODIFICADO] Para que nuestra interfaz gráfica
    // no se superponga con la barra de estado (parte superior) y la
    // barra de navegación (parte inferior), la envolvemos en un Scaffold,
    // igual que hemos hecho en onCreate.
    Unidad2Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MiComposable(modifier = Modifier.padding(innerPadding))
            //MiComposable()
        }
    }
}