package cl.sanaesencia.app.ui

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

private val AzulEncabezado = Color(0xFF7899BC)
private val CelesteEncabezado = Color(0xFFEAF2F9)
private val GrafitoEncabezado = Color(0xFF202020)

@Composable
fun EncabezadoHome(
    onAbrirPerfil: () -> Unit
) {
    var menuPerfilAbierto by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preferencias = remember {
        context.getSharedPreferences(
            "sanaesencia_perfil",
            android.content.Context.MODE_PRIVATE
        )
    }

    var fotoPerfilUri by remember {
        mutableStateOf(preferencias.getString("foto_uri", null))
    }

    DisposableEffect(lifecycleOwner, preferencias) {
        val observador = LifecycleEventObserver { _, evento ->
            if (evento == Lifecycle.Event.ON_RESUME) {
                fotoPerfilUri = preferencias.getString("foto_uri", null)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observador)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observador)
        }
    }

    val fotoPerfil = remember(fotoPerfilUri) {
        fotoPerfilUri?.let { uri ->
            try {
                context.contentResolver.openInputStream(Uri.parse(uri))?.use {
                    val opciones = BitmapFactory.Options().apply {
                        inSampleSize = 4
                    }

                    BitmapFactory.decodeStream(it, null, opciones)
                        ?.asImageBitmap()
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = CelesteEncabezado,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Spa,
                contentDescription = "SanaEsencia",
                tint = AzulEncabezado,
                modifier = Modifier.size(28.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = "SanaEsencia",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = GrafitoEncabezado
            )

            Text(
                text = "Bienestar integral",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }

        Box {
            androidx.compose.material3.IconButton(
                onClick = {
                    menuPerfilAbierto = !menuPerfilAbierto
                }
            ) {
                if (fotoPerfil != null) {
                    Image(
                        bitmap = fotoPerfil,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Abrir menú de usuario",
                        tint = AzulEncabezado,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = menuPerfilAbierto,
                onDismissRequest = {
                    menuPerfilAbierto = false
                }
            ) {
                DropdownMenuItem(
                    text = { Text("Mi perfil") },
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = null)
                    },
                    trailingIcon = {
                        Icon(Icons.Filled.ChevronRight, contentDescription = null)
                    },
                    onClick = {
                        menuPerfilAbierto = false
                        onAbrirPerfil()
                    }
                )

                DropdownMenuItem(
                    text = { Text("Mis consultas") },
                    leadingIcon = {
                        Icon(Icons.Filled.CalendarMonth, contentDescription = null)
                    },
                    trailingIcon = {
                        Icon(Icons.Filled.ChevronRight, contentDescription = null)
                    },
                    onClick = {
                        menuPerfilAbierto = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Tienda de bienestar") },
                    leadingIcon = {
                        Icon(Icons.Filled.Spa, contentDescription = null)
                    },
                    trailingIcon = {
                        Icon(Icons.Filled.ChevronRight, contentDescription = null)
                    },
                    onClick = {
                        menuPerfilAbierto = false
                    }
                )

                HorizontalDivider()

                DropdownMenuItem(
                    text = { Text("Configuración") },
                    leadingIcon = {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                    },
                    onClick = {
                        menuPerfilAbierto = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Cerrar menú") },
                    leadingIcon = {
                        Icon(Icons.Filled.Close, contentDescription = null)
                    },
                    onClick = {
                        menuPerfilAbierto = false
                    }
                )
            }
        }
    }
}
