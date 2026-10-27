package cl.sanaesencia.app.ui.animaciones

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

object TransicionesNavegacion {

    private const val DURACION = 500

    val entrada:
            AnimatedContentTransitionScope<NavBackStackEntry>.() ->
            androidx.compose.animation.EnterTransition = {
        fadeIn(animationSpec = tween(DURACION)) +
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(DURACION)
                )
    }

    val salida:
            AnimatedContentTransitionScope<NavBackStackEntry>.() ->
            androidx.compose.animation.ExitTransition = {
        fadeOut(animationSpec = tween(DURACION)) +
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(DURACION)
                )
    }

    val entradaAtras:
            AnimatedContentTransitionScope<NavBackStackEntry>.() ->
            androidx.compose.animation.EnterTransition = {
        fadeIn(animationSpec = tween(DURACION)) +
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(DURACION)
                )
    }

    val salidaAtras:
            AnimatedContentTransitionScope<NavBackStackEntry>.() ->
            androidx.compose.animation.ExitTransition = {
        fadeOut(animationSpec = tween(DURACION)) +
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(DURACION)
                )
    }
}
