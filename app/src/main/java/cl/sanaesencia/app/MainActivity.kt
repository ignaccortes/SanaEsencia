package cl.sanaesencia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import cl.sanaesencia.app.ui.theme.SanaEsenciaTheme

private val AzulSana = Color(0xFF7899BC)
private val AzulClaro = Color(0xFFEAF2F9)
private val Grafito = Color (0xFF202020)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SanaEsenciaTheme {
                var mostrarRegistro by remember {mutableStateOf(false)}

                if (mostrarRegistro){
                    PantallaRegistro(onVolver = { mostrarRegistro = false })
                } else {
                    PantallaBienvenida(
                        onCrearCuenta = { mostrarRegistro = true }
                    )
                }

            }
        }
    }
}




