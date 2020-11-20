package screens

sealed class TypeScreenState {
    object Loading : TypeScreenState()
    data class Error(val err: Exception) : TypeScreenState()
    object Data : TypeScreenState()
}