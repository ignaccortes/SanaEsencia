package cl.sanaesencia.app.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AzulBotonesBienvenida = Color(0xFF7899BC)

@Composable
fun BotonesBienvenida(
    onCrearCuenta: () -> Unit,
    onIniciarSesion: () -> Unit
) {
    Button(
        onClick = onCrearCuenta,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AzulBotonesBienvenida
        )
    ) {
        Text(
            text = "Crear mi cuenta",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedButton(
        onClick = onIniciarSesion,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(
            text = "Ya tengo una cuenta",
            color = AzulBotonesBienvenida,
            fontSize = 16.sp
        )
    }
}
