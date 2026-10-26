package cl.sanaesencia.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.sanaesencia.app.data.UsuarioRepository
import cl.sanaesencia.app.model.InicioSesionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InicioSesionViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(InicioSesionUiState())
    val uiState: StateFlow<InicioSesionUiState> = _uiState.asStateFlow()

    fun actualizarCorreo(valor: String) {
        _uiState.update {
            it.copy(
                correo = valor,
                errorCorreo = null,
                mensajeError = null
            )
        }
    }

    fun actualizarContrasena(valor: String) {
        _uiState.update {
            it.copy(
                contrasena = valor,
                errorContrasena = null,
                mensajeError = null
            )
        }
    }

    fun iniciarSesion() {
        val estado = _uiState.value

        val errorCorreo = if (
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(estado.correo.trim())
                .matches()
        ) {
            "Ingresa un correo válido"
        } else {
            null
        }

        val errorContrasena = if (estado.contrasena.isBlank()) {
            "Ingresa tu contraseña"
        } else {
            null
        }

        if (errorCorreo != null || errorContrasena != null) {
            _uiState.update {
                it.copy(
                    errorCorreo = errorCorreo,
                    errorContrasena = errorContrasena,
                    mensajeError = null,
                    inicioSesionExitoso = false
                )
            }
            return
        }

        _uiState.update {
            it.copy(
                cargando = true,
                mensajeError = null,
                inicioSesionExitoso = false
            )
        }

        viewModelScope.launch {
            try {
                val usuario = usuarioRepository.iniciarSesion(
                    correo = estado.correo.trim(),
                    contrasena = estado.contrasena
                )

                if (usuario != null) {
                    _uiState.update {
                        it.copy(
                            cargando = false,
                            inicioSesionExitoso = true,
                            mensajeError = null,
                            nombreUsuario = usuario.nombre,
                            correoUsuario = usuario.correo
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            cargando = false,
                            inicioSesionExitoso = false,
                            mensajeError = "Correo o contraseña incorrectos",
                            nombreUsuario = "",
                            correoUsuario = ""
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        cargando = false,
                        inicioSesionExitoso = false,
                        mensajeError = "No se pudo iniciar sesión. Inténtalo nuevamente."
                    )
                }
            }
        }
    }
}
