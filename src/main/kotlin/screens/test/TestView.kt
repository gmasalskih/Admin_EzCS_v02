package screens.test

import androidx.compose.runtime.Composable
import common_widgets.CardImage
import org.koin.core.component.inject
import screens.BaseView

class TestView : BaseView<TestController>() {
    override val controller by inject<TestController>()

    @Composable
    override fun setContent(
        controller: TestController,
    ) {
        CardImage(
            label = "",
            pathToFile = controller.viewState.item.content,
            onClick = controller::test
        )
    }
}