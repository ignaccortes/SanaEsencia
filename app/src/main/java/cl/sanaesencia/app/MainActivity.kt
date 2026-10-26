
package cl.sanaesencia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.sanaesencia.app.data.AppDatabase
import cl.sanaesencia.app.data.UsuarioRepository
import cl.sanaesencia.app.navigation.NavigationEvent
import cl.sanaesencia.app.navigation.Screen
import cl.sanaesencia.app.ui.PantallaBienvenida
import cl.sanaesencia.app.ui.PantallaInicioSesion
import cl.sanaesencia.app.ui.PantallaRegistro
import cl.sanaesencia.app.ui.theme.SanaEsenciaTheme
import cl.sanaesencia.app.ui.PantallaHome
import cl.sanaesencia.app.viewmodels.MainViewModel
import cl.sanaesencia.app.viewmodels.RegistroViewModel
import cl.sanaesencia.app.viewmodels.RegistroViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import cl.sanaesencia.app.viewmodels.InicioSesionViewModel
import cl.sanaesencia.app.viewmodels.InicioSesionViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SanaEsenciaTheme {
                val navController = rememberNavController()
                val mainViewModel: MainViewModel = viewModel()

                LaunchedEffect(Unit) {
                    mainViewModel.navigationEvents.collectLatest { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route.route)
                            }
                            is NavigationEvent.PopBackStack -> {
                                navController.popBackStack()
                            }
                        }
                    }
                }

                val context = LocalContext.current

                val baseDeDatos = remember {
                    AppDatabase.obtenerInstancia(context)
                }

                val usuarioRepository = remember {
                    UsuarioRepository(baseDeDatos.usuarioDao())
                }

                val registroViewModel: RegistroViewModel = viewModel(
                    factory = RegistroViewModelFactory(usuarioRepository)
                )

                val inicioSesionViewModel: InicioSesionViewModel = viewModel(
                    factory = InicioSesionViewModelFactory(usuarioRepository)
                )

                NavHost(
                    navController = navController,
                    startDestination = Screen.Bienvenida.route
                ) {
                    composable(Screen.Bienvenida.route) {
                        PantallaBienvenida(
                            onCrearCuenta = {
                                mainViewModel.navigateTo(Screen.Registro)
                            },
                            onIniciarSesion = {
                                mainViewModel.navigateTo(Screen.InicioSesion)
                            }
                        )
                    }

                    composable(Screen.Registro.route) {
                        PantallaRegistro(
                            onVolver = { mainViewModel.goBack() },
                            registroViewModel = registroViewModel
                        )
                    }

                    composable(Screen.InicioSesion.route) {
                        PantallaInicioSesion(
                            onVolver = { mainViewModel.goBack() },
                            onInicioSesionExitoso = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Bienvenida.route) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            },
                            inicioSesionViewModel = inicioSesionViewModel
                        )
                    }

                    composable(Screen.Home.route) {
                        PantallaHome(
                            nombreUsuario = "Usuario"
                        )
                    }
                }
            }
        }
    }
}




