package cl.sanaesencia.app.model

data class RegistroUiState(
    val nombre: String = "",
    val correo: String = "",
    val contrasena: String = "",
    val confirmarContrasena: String = "",
    val errorNombre: String? = null,
    val errorCorreo: String? = null,
    val errorContrasena: String? = null,
    val errorConfirmarContrasena: String? = null,
    val registroExitoso: Boolean = false,
    val cargando: Boolean = false,
    val mensajeRegistro: String? = null
)
