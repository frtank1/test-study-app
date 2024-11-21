  package kz.secret_santa_jusan.presentation.Auth


import cafe.adriel.voyager.core.model.screenModelScope
import com.example.auth.AuthUseCase
import com.example.auth.AuthUseCaseImpl
import com.example.core.base.CoreBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

interface IAuthViewModel {
    val state: StateFlow<AuthState>
    val navigationEvent: StateFlow<NavigationEvent>
    fun sendEvent(event: AuthEvent)
}

sealed class AuthEvent{
    object Back: AuthEvent()
    class EnterEmail(val text:String):AuthEvent()
    class EnterPassword(val text:String):AuthEvent()
    object Login:AuthEvent()

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

sealed class AuthState{
    data class Default(val email:String = "", val firstPassword:String = ""): AuthState()
}

class AuthViewModelPreview : IAuthViewModel {
    override val state: StateFlow<AuthState> = MutableStateFlow(AuthState.Default()).asStateFlow()
    override val navigationEvent = MutableStateFlow(NavigationEvent.Default()).asStateFlow()
    override fun sendEvent(event: AuthEvent) {}
}

class AuthViewModel(
    private val repository: AuthUseCase
):CoreBaseViewModel(), IAuthViewModel {

    private var _state = MutableStateFlow<AuthState>(AuthState.Default())
    override val state: StateFlow<AuthState> = _state.asStateFlow()


    private val _navigationEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.Default())
    override val navigationEvent: StateFlow<NavigationEvent> = _navigationEvent.asStateFlow()

    init {
        screenModelScope.launch {
        }
    }

    override fun sendEvent(event: AuthEvent) {
        when(event){
            AuthEvent.Back -> {
                _navigationEvent.value = NavigationEvent.Back()
            }

            is AuthEvent.EnterEmail -> {
                val currentState = state.value as AuthState.Default ?: return
                _state.value = currentState.copy(email = event.text)
            }

            is AuthEvent.EnterPassword -> {
                val currentState = state.value as AuthState.Default ?: return
                _state.value = currentState.copy(firstPassword = event.text)
            }

            AuthEvent.Login -> {
                val currentState = state.value as AuthState.Default ?: return
                screenModelScope.launch {
                        repository.loginUser(currentState.email,currentState.firstPassword)
                }
            }
        }
    }
}
