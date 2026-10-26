package cl.sanaesencia.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AzulPerfil = Color(0xFF7899BC)
private val GrafitoPerfil = Color(0xFF202020)

@Composable
fun PantallaPerfil(
    nombreUsuario: String = "Usuario",
    correoUsuario: String = "",
    tieneFoto: Boolean = false,
    onVolver: () -> Unit
) {
    val estadoFoto = recordarGestionFotoPerfil()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = onVolver,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = AzulPerfil
                    )
                }

                Text(
                    text = "Mi perfil",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrafitoPerfil
                )
            }

            Spacer(modifier = Modifier.size(36.dp))

            FotoPerfil(
                nombreUsuario = nombreUsuario,
                correoUsuario = correoUsuario,
                imagen = estadoFoto.imagen,
                tieneFoto = estadoFoto.uri != null || tieneFoto,
                onPedirFoto = estadoFoto.pedirFoto
            )
        }
    }
}
