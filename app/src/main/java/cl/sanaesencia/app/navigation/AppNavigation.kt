package cl.sanaesencia.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cl.sanaesencia.app.ui.PantallaBienvenida
import cl.sanaesencia.app.ui.PantallaInicioSesion
import cl.sanaesencia.app.ui.PantallaRegistro

@Composable
fun AppNavigation() {
    var mostrarRegistro by remember { mutableStateOf(false) }
    var mostrarInicioSesion by remember { mutableStateOf(false) }

    when {
        mostrarRegistro -> {
            PantallaRegistro(
                onVolver = { mostrarRegistro = false }
            )
        }

        mostrarInicioSesion -> {
            PantallaInicioSesion(
                onVolver = { mostrarInicioSesion = false }
            )
        }

        else -> {
            PantallaBienvenida(
                onCrearCuenta = { mostrarRegistro = true },
                onIniciarSesion = { mostrarInicioSesion = true }
            )
        }
    }
}