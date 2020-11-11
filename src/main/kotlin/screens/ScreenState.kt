package screens

sealed class ScreenState {
    object Loading : ScreenState()
    data class Error(val err: Exception) : ScreenState()
    data class Data<VS : ViewState>(val viewState: VS) : ScreenState()
}