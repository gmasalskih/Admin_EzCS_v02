package screens.weapons.add

import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class WeaponsAddView : BaseView<WeaponsAddController>() {
    override val controller by inject<WeaponsAddController>()

    @Composable
    override fun setContent() {
//        TODO("Not yet implemented")
    }
}