package screens.wingman.menu

import data.entitys.Wingman
import screens.ItemViewState

data class WingmanMenuItemViewState(
    val listWingman: List<Wingman> = listOf()
) : ItemViewState {
    override fun isValid(): Boolean = true
}