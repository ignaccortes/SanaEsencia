package cl.sanaesencia.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AzulRegistro = Color(0xFF7899BC)
private val GrafitoRegistro = Color(0xFF202020)

@Composable
fun PantallaRegistro(
    onVolver: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                value = "",
                onValueChange = {},
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulRegistro
                )
            ) {
                Text(
                    text = "Crear cuenta",
                    fontSize = 16.sp
                )

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
}