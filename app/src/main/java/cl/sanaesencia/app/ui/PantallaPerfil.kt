
package cl.sanaesencia.app.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.core.content.ContextCompat
import cl.sanaesencia.app.camera.crearArchivoFotoPerfil

private val AzulPerfil = Color(0xFF7899BC)
private val CelestePerfil = Color(0xFFEAF2F9)
private val GrafitoPerfil = Color(0xFF202020)

@Composable
fun PantallaPerfil(
    nombreUsuario: String = "Usuario",
    correoUsuario: String = "",
    tieneFoto: Boolean = false,
    onVolver: () -> Unit
) {
    val context = LocalContext.current

    val preferencias = remember {
        context.getSharedPreferences(
            "sanaesencia_perfil",
            android.content.Context.MODE_PRIVATE
        )
    }

    var fotoPerfilUri by rememberSaveable {
        mutableStateOf(preferencias.getString("foto_uri", null))
    }

    var uriFotoPendiente by remember {
        mutableStateOf<Uri?>(null)
    }

    val lanzadorCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { guardada ->
        val pendiente = uriFotoPendiente

        if (guardada && pendiente != null) {
            fotoPerfilUri = pendiente.toString()
            preferencias.edit()
                .putString("foto_uri", pendiente.toString())
                .apply()

            Toast.makeText(
                context,
                "Foto de perfil guardada",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "No se guardó la foto",
                Toast.LENGTH_SHORT
            ).show()
        }

        uriFotoPendiente = null
    }

    fun abrirCamara() {
        try {
            val uri = crearArchivoFotoPerfil(context)
            uriFotoPendiente = uri
            lanzadorCamara.launch(uri)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "No se pudo abrir la cámara",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val lanzadorPermiso = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (concedido) {
            abrirCamara()
        } else {
            Toast.makeText(
                context,
                "Debes conceder el permiso para tomar una foto",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun pedirFoto() {
        val permisoConcedido = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (permisoConcedido) {
            abrirCamara()
        } else {
            lanzadorPermiso.launch(Manifest.permission.CAMERA)
        }
    }

    val fotoPerfil = remember(fotoPerfilUri) {
        fotoPerfilUri?.let { uri ->
            try {
                context.contentResolver
                    .openInputStream(Uri.parse(uri))
                    ?.use { input ->
                        val opciones = BitmapFactory.Options().apply {
                            inSampleSize = 4
                        }

                        BitmapFactory.decodeStream(
                            input,
                            null,
                            opciones
                        )?.asImageBitmap()
                    }
            } catch (e: Exception) {
                null
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = onVolver,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = AzulPerfil
                    )
                }

                Text(
                    text = "Mi perfil",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrafitoPerfil
                )
            }

            Spacer(modifier = Modifier.size(36.dp))

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(CelestePerfil),
                contentAlignment = Alignment.Center
            ) {
                if (fotoPerfil != null) {
                    Image(
                        bitmap = fotoPerfil,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Sin foto de perfil",
                        modifier = Modifier.size(64.dp),
                        tint = AzulPerfil
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = nombreUsuario,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = GrafitoPerfil
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
                color = GrafitoPerfil
            )

            Spacer(modifier = Modifier.size(28.dp))

            Button(
                onClick = { pedirFoto() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPerfil
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = if (fotoPerfilUri != null || tieneFoto) {
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
}
