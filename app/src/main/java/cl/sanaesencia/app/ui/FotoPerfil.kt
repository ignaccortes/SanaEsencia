package cl.sanaesencia.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AzulFotoPerfil = Color(0xFF7899BC)
private val CelesteFotoPerfil = Color(0xFFEAF2F9)
private val GrafitoFotoPerfil = Color(0xFF202020)

@Composable
fun FotoPerfil(
    nombreUsuario: String,
    correoUsuario: String,
    imagen: ImageBitmap?,
    tieneFoto: Boolean,
    onPedirFoto: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(CelesteFotoPerfil),
            contentAlignment = Alignment.Center
        ) {
            if (imagen != null) {
                Image(
                    bitmap = imagen,
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Sin foto de perfil",
                    modifier = Modifier.size(64.dp),
                    tint = AzulFotoPerfil
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = nombreUsuario,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = GrafitoFotoPerfil
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = correoUsuario,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "Tu espacio personal en SanaEsencia",
            fontSize = 14.sp,
            color = GrafitoFotoPerfil
        )

        Spacer(modifier = Modifier.size(28.dp))

        Button(
            onClick = onPedirFoto,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AzulFotoPerfil
            )
        ) {
            Icon(
                imageVector = Icons.Filled.CameraAlt,
                contentDescription = null
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = if (tieneFoto) {
                    "Cambiar foto de perfil"
                } else {
                    "Añadir foto de perfil"
                }
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Text(
            text = "Añadir una foto es opcional.",
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}


