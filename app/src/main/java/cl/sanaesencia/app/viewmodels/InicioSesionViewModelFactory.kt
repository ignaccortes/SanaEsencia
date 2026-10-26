package cl.sanaesencia.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.sanaesencia.app.data.UsuarioRepository

class InicioSesionViewModelFactory(
    private val usuarioRepository: UsuarioRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InicioSesionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InicioSesionViewModel(usuarioRepository) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconocido: ${modelClass.name}"
        )
    }
}
