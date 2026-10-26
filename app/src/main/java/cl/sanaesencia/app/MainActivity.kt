
package cl.sanaesencia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.sanaesencia.app.navigation.NavigationEvent
import cl.sanaesencia.app.navigation.Screen
import cl.sanaesencia.app.ui.PantallaBienvenida
import cl.sanaesencia.app.ui.PantallaInicioSesion
import cl.sanaesencia.app.ui.PantallaRegistro
import cl.sanaesencia.app.ui.theme.SanaEsenciaTheme
import cl.sanaesencia.app.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest

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
                            onVolver = {
                                mainViewModel.goBack()
                            }
                        )
                    }

                    composable(Screen.InicioSesion.route) {
                        PantallaInicioSesion(
                            onVolver = {
                                mainViewModel.goBack()
                            }
                        )
                    }
                }
            }
        }
    }
}




