package data.types

sealed class StateType {
    object Loading : StateType()
    data class Error(val err: Throwable) : StateType()
    object Data : StateType()
}