package cl.sanaesencia.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequisitosContrasena(contrasena: String) {
    val requisitos = listOf(
        "8 caracteres como mínimo" to (contrasena.length >= 8),
        "Una letra mayúscula" to contrasena.any { it.isUpperCase() },
        "Una letra minúscula" to contrasena.any { it.isLowerCase() },
        "Al menos un número" to contrasena.any { it.isDigit() },
        "Un carácter especial" to contrasena.any { !it.isLetterOrDigit() }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 4.dp)
    ) {
        Text(
            text = "Tu contraseña debe tener:",
            fontSize = 12.sp,
            color = Color(0xFF202020)
        )

        Spacer(modifier = Modifier.height(6.dp))

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
}
