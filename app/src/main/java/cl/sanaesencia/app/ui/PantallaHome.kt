package cl.sanaesencia.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val AzulHome = Color(0xFF7899BC)
private val GrafitoHome = Color(0xFF202020)

@Composable
fun PantallaHome(
    nombreUsuario: String = "Usuario",
    onAbrirPerfil: () -> Unit
) {
    var mostrarTarjetas by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(700)
        mostrarTarjetas = true
    }

    var mostrarContenido by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        mostrarContenido = true
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 24.dp)
        ) {
            EncabezadoHome(
                onAbrirPerfil = onAbrirPerfil
            )

            Spacer(modifier = Modifier.height(32.dp))

            AnimatedVisibility(
                visible = mostrarContenido,
                enter = fadeIn(
                    animationSpec = tween(500)
                ) + slideInVertically(
                    initialOffsetY = { it / 5 },
                    animationSpec = tween(500)
                )
            ) {
                SaludoHome(nombreUsuario = nombreUsuario)
            }

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedVisibility(
                visible = mostrarContenido,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 600,
                        delayMillis = 150
                    )
                ) + slideInVertically(
                    initialOffsetY = { it / 5 },
                    animationSpec = tween(
                        durationMillis = 600,
                        delayMillis = 150
                    )
                )
            ) {
                TarjetaBienvenida()
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Explora SanaEsencia",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = GrafitoHome
            )

            Spacer(modifier = Modifier.height(16.dp))

            TarjetaAccesoAnimada(visible = mostrarTarjetas) {
                TarjetaAccesoHome(
                    icono = {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = AzulHome,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    titulo = "Mis consultas",
                    descripcion = "Revisa tus próximas sesiones"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TarjetaAccesoAnimada(visible = mostrarTarjetas) {
                TarjetaAccesoHome(
                    icono = {
                        Icon(
                            imageVector = Icons.Filled.Spa,
                            contentDescription = null,
                            tint = AzulHome,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    titulo = "Tienda de bienestar",
                    descripcion = "Descubre productos para ti"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TarjetaAccesoAnimada(visible = mostrarTarjetas) {
                TarjetaAccesoHome(
                    icono = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = AzulHome,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    titulo = "Mi perfil",
                    descripcion = "Consulta tus datos personales"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Un espacio para cuidar de ti",
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}
