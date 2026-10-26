package cl.sanaesencia.app.camera

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun crearArchivoFotoPerfil(context: Context): Uri {
    val carpetaFotos = File(context.filesDir, "profile_photos")

    if (!carpetaFotos.exists()) {
        carpetaFotos.mkdirs()
    }

    val archivoFoto = File(
        carpetaFotos,
        "perfil_${System.currentTimeMillis()}.jpg"
    )

    archivoFoto.createNewFile()

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        archivoFoto
    )
}
