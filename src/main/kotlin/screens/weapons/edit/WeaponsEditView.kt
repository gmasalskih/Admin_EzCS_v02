package screens.weapons.edit

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class WeaponsEditView(val id: String) : BaseView<WeaponsEditController>() {
    override val controller by inject<WeaponsEditController>()

    @Composable
    override fun setContent() {
//        TODO("Not yet implemented")
    }
}