package screens.competitive.menu

import data.entitys.Competitive
import screens.ItemViewState

data class CompetitiveMenuItemViewState(
    val listCompetitive: List<Competitive> = listOf()
) : ItemViewState {
    override fun isValid(): Boolean = true
}