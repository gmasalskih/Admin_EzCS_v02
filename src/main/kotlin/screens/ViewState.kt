package screens

data class ViewState<I>(
    val typeScreenState: TypeScreenState = TypeScreenState.Data,
    val title: String = "",
    val item: I
)