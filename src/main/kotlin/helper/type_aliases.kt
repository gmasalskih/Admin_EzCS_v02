package helper

import screens.BaseController
import screens.BaseView
import screens.ViewState
import kotlin.reflect.KFunction1

typealias Controller = BaseController<out ViewState>
typealias ViewComponent = BaseView<out ViewState, out Controller>