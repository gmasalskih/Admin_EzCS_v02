package screens.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.koin.core.inject
import screens.BaseView

class TestView : BaseView<TestController>() {
    override val controller by inject<TestController>()

    @Composable
    override fun setContent(controller: TestController) {
        Text("Test")
    }
}