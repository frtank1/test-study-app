  package com.example.registration


import cafe.adriel.voyager.core.model.screenModelScope
import com.example.auth.AuthUseCase
import com.example.core.base.CoreBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

interface IRegistrationViewModel {
    val state: StateFlow<RegistrationState>
    val navigationEvent: StateFlow<NavigationEvent>
    fun sendEvent(event: RegistrationEvent)
}

sealed class RegistrationEvent{
    object Back: RegistrationEvent()
    class EnterEmail(val text:String):RegistrationEvent()
    class EnterPassword(val text:String):RegistrationEvent()
    class EnterSecondPassword(val text:String):RegistrationEvent()
    object Registrate:RegistrationEvent()
}

sealed class NavigationEvent{
    private var handled: Boolean = false

    fun getValue(): NavigationEvent {
        if (handled) return Default()
        handled = true
        return this
    }
    class Default: NavigationEvent()
    class Back: NavigationEvent()
}

sealed class RegistrationState{
   data class Default(val email:String = "", val firstPassword:String = "", val secondPassword:String = ""): RegistrationState()
}

class RegistrationViewModelPreview : IRegistrationViewModel {
    override val state: StateFlow<RegistrationState> = MutableStateFlow(RegistrationState.Default()).asStateFlow()
    override val navigationEvent = MutableStateFlow(NavigationEvent.Default()).asStateFlow()
    override fun sendEvent(event: RegistrationEvent) {}
}

class RegistrationViewModel(
    private val repository: AuthUseCase
):CoreBaseViewModel(), IRegistrationViewModel {

    private var _state = MutableStateFlow<RegistrationState>(RegistrationState.Default(""))
    override val state: StateFlow<RegistrationState> = _state.asStateFlow()


    private val _navigationEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.Default())
    override val navigationEvent: StateFlow<NavigationEvent> = _navigationEvent.asStateFlow()


    override fun sendEvent(event: RegistrationEvent) {
        when(event){
            RegistrationEvent.Back -> {
                _navigationEvent.value = NavigationEvent.Back()
            }

            is RegistrationEvent.EnterEmail -> {
                val currentState = state.value as RegistrationState.Default ?: return
                _state.value = currentState.copy(email = event.text)
            }

            RegistrationEvent.Registrate -> {
                val currentState = state.value as RegistrationState.Default ?: return
                screenModelScope.launch {
                    if (currentState.firstPassword.equals(currentState.secondPassword))
                    repository.registerUser(currentState.email,currentState.firstPassword)
                }
            }

            is RegistrationEvent.EnterPassword -> {
                val currentState = state.value as RegistrationState.Default ?: return
                _state.value = currentState.copy(firstPassword = event.text)
            }
            is RegistrationEvent.EnterSecondPassword -> {
                val currentState = state.value as RegistrationState.Default ?: return
                _state.value = currentState.copy(secondPassword = event.text)
            }
        }
    }
}
