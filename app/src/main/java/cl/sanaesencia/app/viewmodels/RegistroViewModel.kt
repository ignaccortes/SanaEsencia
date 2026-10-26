package cl.sanaesencia.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.sanaesencia.app.data.Usuario
import cl.sanaesencia.app.data.UsuarioRepository
import cl.sanaesencia.app.model.RegistroUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistroViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    fun actualizarNombre(valor: String) {
        _uiState.update {
            it.copy(nombre = valor, errorNombre = null, mensajeRegistro = null)
        }
    }

    fun actualizarCorreo(valor: String) {
        _uiState.update {
            it.copy(correo = valor, errorCorreo = null, mensajeRegistro = null)
        }
    }

    fun actualizarContrasena(valor: String) {
        _uiState.update {
            it.copy(contrasena = valor, errorContrasena = null, mensajeRegistro = null)
        }
    }

    fun actualizarConfirmacionContrasena(valor: String) {
        _uiState.update {
            it.copy(
                confirmarContrasena = valor,
                errorConfirmarContrasena = null,
                mensajeRegistro = null
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

        val hayErrores = errorNombre != null ||
                errorCorreo != null ||
                errorContrasena != null ||
                errorConfirmarContrasena != null

        _uiState.update {
            it.copy(
                errorNombre = errorNombre,
                errorCorreo = errorCorreo,
                errorContrasena = errorContrasena,
                errorConfirmarContrasena = errorConfirmarContrasena,
                registroExitoso = false,
                mensajeRegistro = null
            )
        }

        if (hayErrores) return

        registrarUsuarioEnBaseDeDatos()
    }

    private fun registrarUsuarioEnBaseDeDatos() {
        val estado = _uiState.value

        _uiState.update {
            it.copy(cargando = true, mensajeRegistro = null)
        }

        viewModelScope.launch {
            try {
                val usuario = Usuario(
                    nombre = estado.nombre.trim(),
                    correo = estado.correo.trim(),
                    contrasena = estado.contrasena
                )

                val registrado = usuarioRepository.registrarUsuario(usuario)

                if (registrado) {
                    _uiState.update {
                        it.copy(
                            cargando = false,
                            registroExitoso = true,
                            mensajeRegistro = "Cuenta creada correctamente"
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            cargando = false,
                            registroExitoso = false,
                            errorCorreo = "Ya existe una cuenta con este correo",
                            mensajeRegistro = null
                        )
                    }
                }
            } catch (_: Exception) {
                _uiState.update {
                    it.copy(
                        cargando = false,
                        registroExitoso = false,
                        mensajeRegistro = "No se pudo crear la cuenta. Inténtalo nuevamente."
                    )
                }
            }
        }
    }
}
