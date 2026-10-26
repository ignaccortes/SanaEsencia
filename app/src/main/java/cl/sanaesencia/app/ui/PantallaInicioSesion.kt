package cl.sanaesencia.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.sanaesencia.app.viewmodels.InicioSesionViewModel

private val AzulLogin = Color(0xFF7899BC)
private val GrafitoLogin = Color(0xFF202020)

@Composable
fun PantallaInicioSesion(
    onVolver: () -> Unit,
    onInicioSesionExitoso: () -> Unit,
    inicioSesionViewModel: InicioSesionViewModel
) {
    val uiState by inicioSesionViewModel.uiState.collectAsState()

    LaunchedEffect(uiState.inicioSesionExitoso) {
        if (uiState.inicioSesionExitoso) {
            onInicioSesionExitoso()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido de nuevo",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = GrafitoLogin
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Continúa cuidando de tu bienestar",
                fontSize = 15.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(28.dp))

            CamposInicioSesion(
                correo = uiState.correo,
                contrasena = uiState.contrasena,
                errorCorreo = uiState.errorCorreo,
                errorContrasena = uiState.errorContrasena,
                onCorreoChange = inicioSesionViewModel::actualizarCorreo,
                onContrasenaChange = inicioSesionViewModel::actualizarContrasena
            )

            Spacer(modifier = Modifier.height(16.dp))

            uiState.mensajeError?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = Color.Red,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = {
                    inicioSesionViewModel.iniciarSesion()
                },
                enabled = !uiState.cargando,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulLogin
                )
            ) {
                Text(
                    text = if (uiState.cargando) {
                        "Verificando..."
                    } else {
                        "Iniciar sesión"
                    },
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = onVolver) {
                Text(
                    text = "Volver",
                    color = AzulLogin
                )
            }
        }
    }
}
