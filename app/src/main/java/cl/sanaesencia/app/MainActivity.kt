package cl.sanaesencia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cl.sanaesencia.app.ui.theme.SanaEsenciaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SanaEsenciaTheme {
                var mostrarRegistro by remember {mutableStateOf(false)}
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
        }
    }
}




