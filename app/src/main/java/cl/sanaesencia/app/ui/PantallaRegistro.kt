package cl.sanaesencia.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.sanaesencia.app.viewmodels.RegistroViewModel
import cl.sanaesencia.app.viewmodels.RegistroViewModelFactory

private val AzulRegistro = Color(0xFF7899BC)
private val GrafitoRegistro = Color(0xFF202020)

@Composable
fun PantallaRegistro(
    onVolver: () -> Unit,
    registroViewModel: RegistroViewModel
) {
    val uiState by registroViewModel.uiState.collectAsState()
    var mostrarContrasena by remember { mutableStateOf(false) }
    var mostrarConfirmacion by remember { mutableStateOf(false) }
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
                text = "Comienza a cuidar de tu bienestar",
                fontSize = 15.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(28.dp))

            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = registroViewModel::actualizarNombre,
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                isError = uiState.errorNombre != null,
                supportingText = {
                    uiState.errorNombre?.let { Text(it) }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.correo,
                onValueChange = registroViewModel::actualizarCorreo,
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                isError = uiState.errorCorreo != null,
                supportingText = {
                    uiState.errorCorreo?.let { Text(it) }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))


            OutlinedTextField(
                value = uiState.contrasena,
                onValueChange = registroViewModel::actualizarContrasena,
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                visualTransformation = if (mostrarContrasena) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(onClick = {
                        mostrarContrasena = !mostrarContrasena
                    }) {
                        Icon(
                            imageVector = if (mostrarContrasena) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            contentDescription = if (mostrarContrasena) {
                                "Ocultar contraseña"
                            } else {
                                "Mostrar contraseña"
                            }
                        )
                    }
                },
                isError = uiState.errorContrasena != null,
                supportingText = {
                    uiState.errorContrasena?.let { Text(it) }
                }
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 4.dp)
            ) {
                Text(
                    text = "Tu contraseña debe tener:",
                    fontSize = 12.sp,
                    color = GrafitoRegistro
                )

                Spacer(modifier = Modifier.height(6.dp))

                val requisitos = listOf(
                    ("8 caracteres como mínimo" to
                            (uiState.contrasena.length >= 8)),
                    ("Una letra mayúscula" to
                            uiState.contrasena.any { it.isUpperCase() }),
                    ("Una letra minúscula" to
                            uiState.contrasena.any { it.isLowerCase() }),
                    ("Al menos un número" to
                            uiState.contrasena.any { it.isDigit() }),
                    ("Un carácter especial" to
                            uiState.contrasena.any { !it.isLetterOrDigit() })
                )

                requisitos.forEach { (texto, cumplido) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (cumplido) "✓" else "○",
                            color = if (cumplido) {
                                Color(0xFF4A8B65)
                            } else {
                                Color.Gray
                            },
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = texto,
                            color = if (cumplido) {
                                Color(0xFF4A8B65)
                            } else {
                                Color.Gray
                            },
                            fontSize = 12.sp
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(12.dp))


            OutlinedTextField(
                value = uiState.confirmarContrasena,
                onValueChange = registroViewModel::actualizarConfirmacionContrasena,
                label = { Text("Confirmar contraseña") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                visualTransformation = if (mostrarConfirmacion) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            mostrarConfirmacion = !mostrarConfirmacion
                        }
                    ) {
                        Icon(
                            imageVector = if (mostrarConfirmacion) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            contentDescription = if (mostrarConfirmacion) {
                                "Ocultar contraseña"
                            } else {
                                "Mostrar contraseña"
                            }
                        )
                    }
                },
                isError = uiState.errorConfirmarContrasena != null,
                supportingText = {
                    uiState.errorConfirmarContrasena?.let { Text(it) }
                }
            )



            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    registroViewModel.validarRegistro()
                },
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

            uiState.mensajeRegistro?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = if (uiState.registroExitoso) {
                        AzulRegistro
                    } else {
                        GrafitoRegistro
                    }
                )
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
    }