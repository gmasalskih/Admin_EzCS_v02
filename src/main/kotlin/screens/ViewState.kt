package screens

import data.types.StateType

data class ViewState<I : ItemViewState>(
    val stateType: StateType = StateType.Data,
    val title: String = "",
    val item: I
)