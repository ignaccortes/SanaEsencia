package cl.sanaesencia.app.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import cl.sanaesencia.app.camera.crearArchivoFotoPerfil

data class EstadoFotoPerfil(
    val uri: String?,
    val imagen: ImageBitmap?,
    val pedirFoto: () -> Unit
)

@Composable
fun recordarGestionFotoPerfil(): EstadoFotoPerfil {
    val context = LocalContext.current

    val preferencias = remember {
        context.getSharedPreferences(
            "sanaesencia_perfil",
            Context.MODE_PRIVATE
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

    return EstadoFotoPerfil(
        uri = fotoPerfilUri,
        imagen = fotoPerfil,
        pedirFoto = ::pedirFoto
    )
}


