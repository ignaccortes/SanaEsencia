package cl.sanaesencia.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.sanaesencia.app.navigation.NavigationEvent
import cl.sanaesencia.app.navigation.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _navigationEvents = Channel<NavigationEvent>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

    fun navigateTo(screen: Screen) {
        viewModelScope.launch {
            _navigationEvents.send(
                NavigationEvent.NavigateTo(screen)
            )
        }
    }

    fun goBack() {
        viewModelScope.launch {
            _navigationEvents.send(
                NavigationEvent.PopBackStack
            )
        }
    }
}


