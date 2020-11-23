package di

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.StorageClient
import utils.BUCKET_NAME
import utils.DATABASE_URL
import utils.FULL_PATH_TO_SECRET_KEY
import org.koin.dsl.module
import providers.firebase.FirebaseAppProvider
import providers.firebase.StorageProvider
import router.NavigationTargets
import router.Router
import screens.map_points.MapPointsController
import screens.maps.MapsController
import screens.maps.MapsView
import screens.maps_add.MapsAddController
import screens.maps_edit.MapsEditController

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
}

val controllersModule = module {
    single<MapsController> { MapsController() }
    single<MapPointsController> { MapPointsController() }
    single<MapsAddController> { MapsAddController() }
    single<MapsEditController> { MapsEditController() }
}