package screens.competitive.menu

import data.entitys.Competitive
import screens.State

data class CompetitiveMenuState(
    val listCompetitive: List<Competitive> = listOf()
) : State {
    override fun isValid(): Boolean = true
}