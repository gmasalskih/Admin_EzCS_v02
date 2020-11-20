package di

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.StorageClient
import helper.BUCKET_NAME
import helper.DATABASE_URL
import helper.FULL_PATH_TO_SECRET_KEY
import org.koin.dsl.module
import providers.firebase.FirebaseAppProvider
import providers.firebase.StorageProvider
import router.NavigationTargets
import router.Router
import screens.map_points.MapPointsController
import screens.maps.MapsController
import screens.maps.MapsView

val fbModules = module {
    single<FirebaseApp> {
        FirebaseAppProvider(
            fullPathToSecretKey = FULL_PATH_TO_SECRET_KEY,
            databaseUrl = DATABASE_URL
        ).getApp()
    }
    single<StorageClient> { StorageClient.getInstance(get()) }
    single<StorageProvider> { StorageProvider(get(), BUCKET_NAME) }
}
val appModule = module {
    single<Router> { Router(entryPoint = NavigationTargets.Maps to MapsView()) }
    single<MapsController> { MapsController() }
    single<MapPointsController> { MapPointsController() }
}
