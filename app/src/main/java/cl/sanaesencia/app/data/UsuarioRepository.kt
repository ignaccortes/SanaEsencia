package cl.sanaesencia.app.data

class UsuarioRepository(
    private val usuarioDao: UsuarioDao
) {

    suspend fun registrarUsuario(usuario: Usuario): Boolean {
        val usuarioExistente =
            usuarioDao.buscarPorCorreo(usuario.correo.trim())

        if (usuarioExistente != null) {
            return false
        }

        usuarioDao.insertar(
            usuario.copy(correo = usuario.correo.trim())
        )

        return true
    }

    suspend fun iniciarSesion(
        correo: String,
        contrasena: String
    ): Usuario? {
        return usuarioDao.validarCredenciales(
            correo.trim(),
            contrasena
        )
    }
}

