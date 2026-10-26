package cl.sanaesencia.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AzulBienvenida = Color(0xFF7899BC)
private val CelesteBienvenida = Color(0xFFEAF2F9)
private val GrafitoBienvenida = Color(0xFF202020)

@Composable
fun TarjetaBienvenida() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = CelesteBienvenida
        )
    ) {
        Column(
            modifier = Modifier.padding(22.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = AzulBienvenida,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Tu bienestar importa",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = GrafitoBienvenida
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Dedica un momento de tu día a conectar contigo y cuidar tu equilibrio.",
                fontSize = 14.sp,
                color = GrafitoBienvenida,
                lineHeight = 21.sp
            )
        }
    }
}


