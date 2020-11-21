package utils

import screens.BaseController
import screens.BaseView
import screens.ViewState

typealias Controller = BaseController<out ViewState>
typealias ViewComponent = BaseView<out Controller>