package cl.sanaesencia.app.navigation

sealed class Screen(val route: String) {
    data object Bienvenida : Screen("bienvenida")
    data object Registro : Screen("registro")
    data object InicioSesion : Screen("inicio_sesion")

    data object Home : Screen("home")
}

