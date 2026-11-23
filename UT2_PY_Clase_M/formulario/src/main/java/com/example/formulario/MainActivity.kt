package com.example.formulario

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenu
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formulario.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaRegistro()
        }
    }
} // Fin de la clase.

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaRegistroPreview() {
    PantallaRegistro()
}

@Composable
private fun PantallaRegistro() {
    MyApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            Text(text = "Formulario", modifier = Modifier.padding(innerPadding))
            FormulacioRegistro(modifier = Modifier.padding(innerPadding))

        }
    }
}

@Composable
fun FormulacioRegistro(modifier: Modifier = Modifier) {
    var mensaje by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var acepta by remember { mutableStateOf(false) }
    val opciones = listOf("Presencial", "On-line", "Mixta")
    var modalidad by remember { mutableStateOf(opciones[0]) }
    val nombreError = nombre.isBlank()
    var intentoEnvio by remember { mutableStateOf(value = false) }
    var registroCorrecto by remember { mutableStateOf(false) }
    val emailError = !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val passwordError = contrasenia.length < 6
    val formularioValido = !nombreError && !emailError && !passwordError && acepta

    // Necesarias para Menú deplegabable
    var curso by remember { mutableStateOf("") }
    var expandido by remember { mutableStateOf(false) }
    val cursos = listOf("1ºDAM", "2ºDAM")


//    var mensaje = ""
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .imePadding() //Para que el teclado no tape elementos
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )

        {
            Text("Registro", style = MaterialTheme.typography.headlineMedium)

            Text(mensaje)
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre *") },
                singleLine = true,
                isError = intentoEnvio && nombreError,
                supportingText = {
                    if (intentoEnvio && nombreError) Text("Nombre obligatorio")
                    else Text("* Obligatorio")
                },
                modifier = Modifier.fillMaxWidth()


            )
            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellido") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email *") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = intentoEnvio && emailError,
                supportingText = {
                    if (intentoEnvio && emailError) Text("email incorrecto")
                    else Text("* Obligatorio")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = contrasenia,
                onValueChange = { contrasenia = it },
                label = { Text("Constraseña *") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                isError = intentoEnvio && passwordError,
                supportingText = {
                    if (intentoEnvio && passwordError) Text("Contaseña demasiado corta")
                    else Text("* Obligatorio")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("edad") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("telefono") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )



            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = acepta,
                    onCheckedChange = { acepta = it }
                )
                Text("Acepto los términos *")
            }

            if (intentoEnvio && !acepta) {
                Text("Acepta las condiciones", color = MaterialTheme.colorScheme.error) // He añadido color de error para que se vea mejor el mensaje de error.
            }

            Text(text = "Modalidad", style = MaterialTheme.typography.headlineMedium)
            for (opcion in opciones) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = opcion == modalidad, onClick = { modalidad = opcion })
                    Text(opcion)
                }
            }
            ExposedDropdownMenuBox(
                expanded = expandido,
                onExpandedChange = { expandido = it }
            ) {
                OutlinedTextField(
                    value = curso,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Curso") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandido) },
                    modifier = Modifier
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandido,
                    onDismissRequest = { expandido = false }
                ) {
                    for (opcion in cursos) {
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                curso = opcion
                                expandido = false
                            }
                        )
                    }
                }
            }
            Button(
                onClick = {
                    intentoEnvio = true
                    // [SOLUCIÓN ERROR] El resultado solo se actualiza al pulsar Registrarse.
                    registroCorrecto = formularioValido
                },
                modifier = Modifier.fillMaxWidth())
            {
                Text("Registrarse")
            }

            if (registroCorrecto && formularioValido) {
                Text("Registro correcto")
            }
        }

    }

}
