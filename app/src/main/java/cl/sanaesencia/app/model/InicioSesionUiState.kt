package cl.sanaesencia.app.model

data class InicioSesionUiState(
    val correo: String = "",
    val contrasena: String = "",
    val errorCorreo: String? = null,
    val errorContrasena: String? = null,
    val mensajeError: String? = null,
    val cargando: Boolean = false,
    val inicioSesionExitoso: Boolean = false,
    val nombreUsuario: String = "",
    val correoUsuario: String = ""
)
