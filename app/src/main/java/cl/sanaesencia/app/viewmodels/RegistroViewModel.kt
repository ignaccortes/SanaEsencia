package cl.sanaesencia.app.viewmodels

import androidx.lifecycle.ViewModel
import cl.sanaesencia.app.model.RegistroUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistroViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    fun actualizarNombre(valor: String) {
        _uiState.update {
            it.copy(nombre = valor, errorNombre = null)
        }
    }

    fun actualizarCorreo(valor: String) {
        _uiState.update {
            it.copy(correo = valor, errorCorreo = null)
        }
    }

    fun actualizarContrasena(valor: String) {
        _uiState.update {
            it.copy(contrasena = valor, errorContrasena = null)
        }
    }

    fun actualizarConfirmacionContrasena(valor: String) {
        _uiState.update {
            it.copy(
                confirmarContrasena = valor,
                errorConfirmarContrasena = null
            )
        }
    }

    fun validarRegistro() {
        val estado = _uiState.value

        val errorNombre = if (estado.nombre.isBlank()) {
            "Ingresa tu nombre"
        } else null

        val errorCorreo = if (
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(estado.correo.trim())
                .matches()
        ) {
            "Ingresa un correo válido"
        } else null

        val contrasenaValida =
            estado.contrasena.length >= 8 &&
                    estado.contrasena.any { it.isUpperCase() } &&
                    estado.contrasena.any { it.isLowerCase() } &&
                    estado.contrasena.any { it.isDigit() } &&
                    estado.contrasena.any { !it.isLetterOrDigit() }

        val errorContrasena = if (!contrasenaValida) {
            "La contraseña no cumple todos los requisitos"
        } else null

        val errorConfirmarContrasena = if (
            estado.confirmarContrasena != estado.contrasena
        ) {
            "Las contraseñas no coinciden"
        } else null

        _uiState.update {
            it.copy(
                errorNombre = errorNombre,
                errorCorreo = errorCorreo,
                errorContrasena = errorContrasena,
                errorConfirmarContrasena = errorConfirmarContrasena,
                registroExitoso = errorNombre == null &&
                        errorCorreo == null &&
                        errorContrasena == null &&
                        errorConfirmarContrasena == null
            )
        }
    }
}
