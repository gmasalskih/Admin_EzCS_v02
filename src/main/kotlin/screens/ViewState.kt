package screens

import data.types.StateType

data class ViewState<I : State>(
    val stateType: StateType = StateType.Data,
    val title: String = "",
    val item: I
)