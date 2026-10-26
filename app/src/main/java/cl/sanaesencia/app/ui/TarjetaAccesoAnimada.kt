package cl.sanaesencia.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable

@Composable
fun TarjetaAccesoAnimada(
    visible: Boolean,
    contenido: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 500)
        ) + slideInVertically(
            initialOffsetY = { it / 4 },
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        contenido()
    }
}


