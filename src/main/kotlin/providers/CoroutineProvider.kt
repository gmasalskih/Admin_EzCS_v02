package providers

import kotlinx.coroutines.CoroutineScope

interface CoroutineProvider: CoroutineScope {
    fun onStart()
    fun onStop()
}