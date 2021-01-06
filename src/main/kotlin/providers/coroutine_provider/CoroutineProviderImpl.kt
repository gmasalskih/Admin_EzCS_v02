package providers.coroutine_provider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import providers.CoroutineProvider
import kotlin.coroutines.CoroutineContext

class CoroutineProviderImpl : CoroutineProvider {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    init {
        println("Create CoroutineProviderImpl")
    }

    override fun onStart() {
        job = Job()
    }

    override fun onStop() {
        job.cancel()
    }
}