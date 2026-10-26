package cl.sanaesencia.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val GrafitoSaludo = Color(0xFF202020)

@Composable
fun SaludoHome(
    nombreUsuario: String
) {
    Column {
        Text(
            text = "Hola, $nombreUsuario 👋",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = GrafitoSaludo
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "¿Cómo te gustaría cuidar de ti hoy?",
            fontSize = 16.sp,
            color = Color.DarkGray
        )
    }
}


