package data.types

sealed class StateType {
    object Loading : StateType()
    data class Error(val err: Exception) : StateType()
    object Data : StateType()
}