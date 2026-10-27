package cl.sanaesencia.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.sanaesencia.app.viewmodels.RegistroViewModel

private val AzulRegistro = Color(0xFF7899BC)
private val GrafitoRegistro = Color(0xFF202020)

@Composable
fun PantallaRegistro(
    onVolver: () -> Unit,
    onIrInicioSesion: () -> Unit,
    registroViewModel: RegistroViewModel
) {
    val uiState by registroViewModel.uiState.collectAsState()

    var mostrarDialogoExito by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(uiState.registroExitoso) {
        if (uiState.registroExitoso) {
            mostrarDialogoExito = true
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
                text = "Crear cuenta",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = GrafitoRegistro
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "El primer paso para cuidar de ti",
                fontSize = 15.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(28.dp))

            CamposRegistro(
                nombre = uiState.nombre,
                correo = uiState.correo,
                contrasena = uiState.contrasena,
                confirmarContrasena = uiState.confirmarContrasena,
                errorNombre = uiState.errorNombre,
                errorCorreo = uiState.errorCorreo,
                errorContrasena = uiState.errorContrasena,
                errorConfirmarContrasena = uiState.errorConfirmarContrasena,
                onNombreChange = registroViewModel::actualizarNombre,
                onCorreoChange = registroViewModel::actualizarCorreo,
                onContrasenaChange = registroViewModel::actualizarContrasena,
                onConfirmarContrasenaChange =
                    registroViewModel::actualizarConfirmacionContrasena
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    registroViewModel.validarRegistro()
                },
                enabled = !uiState.cargando && !uiState.registroExitoso,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulRegistro
                )
            ) {
                Text(
                    text = if (uiState.cargando) {
                        "Creando cuenta..."
                    } else {
                        "Crear cuenta"
                    },
                    fontSize = 16.sp
                )
            }

            if (uiState.cargando) {
                Text(
                    text = "Creando tu cuenta...",
                    color = GrafitoRegistro
                )
            }

            // Los errores se mantienen visibles.
            // El mensaje de éxito se muestra únicamente en el popup.
            if (!uiState.registroExitoso) {
                uiState.mensajeRegistro?.let { mensaje ->
                    Text(
                        text = mensaje,
                        color = GrafitoRegistro
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = onVolver
            ) {
                Text(
                    text = "Volver",
                    color = AzulRegistro
                )
            }
        }
    }

    if (mostrarDialogoExito) {
        AlertDialog(
            onDismissRequest = {
                // El usuario debe confirmar con OK.
            },
            title = {
                Text("¡Cuenta creada con éxito!")
            },
            text = {
                Text(
                    "Tu cuenta en SanaEsencia está lista. " +
                            "Ya puedes iniciar sesión."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoExito = false
                        onIrInicioSesion()
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}
