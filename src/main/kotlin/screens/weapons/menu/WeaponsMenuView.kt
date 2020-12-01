package screens.weapons.menu

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class WeaponsMenuView : BaseView<WeaponsMenuController>() {
    override val controller by inject<WeaponsMenuController>()

    @Composable
    override fun setContent(controller: WeaponsMenuController) {
//        TODO("Not yet implemented")
    }
}