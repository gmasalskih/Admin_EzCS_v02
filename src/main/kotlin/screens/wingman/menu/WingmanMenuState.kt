package screens.wingman.menu

import data.entitys.Wingman
import screens.State

data class WingmanMenuState(
    val listWingman: List<Wingman> = listOf()
) : State {
    override fun isValid(): Boolean = true
}