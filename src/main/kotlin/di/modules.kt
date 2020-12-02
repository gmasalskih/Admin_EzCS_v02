package di

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.StorageClient
import utils.BUCKET_NAME
import utils.DATABASE_URL
import utils.FULL_PATH_TO_SECRET_KEY
import org.koin.dsl.module
import providers.firebase.FirebaseAppProvider
import providers.firebase.FirestoreProvider
import providers.firebase.StorageProvider
import router.NavigationTargets
import router.Router
import screens.competitive.add.CompetitiveAddController
import screens.competitive.edit.CompetitiveEditController
import screens.competitive.menu.CompetitiveMenuController
import screens.danger_zone.add.DangerZoneAddController
import screens.danger_zone.edit.DangerZoneEditController
import screens.danger_zone.menu.DangerZoneMenuController
import screens.map_points.menu.MapPointsMenuController
import screens.map_points.add.MapPointsAddController
import screens.map_points.edit.MapPointsEditController
import screens.map_holder.menu.MapHolderMenuController
import screens.map_holder.menu.MapHolderMenuView
import screens.map_holder.add.MapHolderAddController
import screens.map_holder.edit.MapHolderEditController
import screens.profile_rank.add.ProfileRankAddController
import screens.profile_rank.edit.ProfileRankEditController
import screens.profile_rank.menu.ProfileRankMenuController
import screens.weapons.add.WeaponsAddController
import screens.weapons.edit.WeaponsEditController
import screens.weapons.menu.WeaponsMenuController
import screens.wingman.add.WingmanAddController
import screens.wingman.edit.WingmanEditController
import screens.wingman.menu.WingmanMenuController

val fbModules = module {
    single<FirebaseApp> {
        FirebaseAppProvider(
            fullPathToSecretKey = FULL_PATH_TO_SECRET_KEY,
            databaseUrl = DATABASE_URL
        ).getApp()
    }
    single<StorageClient> { StorageClient.getInstance(get()) }
    single<StorageProvider> { StorageProvider(get(), BUCKET_NAME) }
    single<FirestoreProvider> { FirestoreProvider(get()) }
}
val appModule = module {
    single<Router> { Router(entryPoint = NavigationTargets.MapHolderMenu to MapHolderMenuView()) }
}

val competitiveModule = module {
    single<CompetitiveAddController> { CompetitiveAddController() }
    single<CompetitiveEditController> { CompetitiveEditController() }
    single<CompetitiveMenuController> { CompetitiveMenuController() }
}

val dangerZoneModule = module {
    single<DangerZoneAddController> { DangerZoneAddController() }
    single<DangerZoneEditController> { DangerZoneEditController() }
    single<DangerZoneMenuController> { DangerZoneMenuController() }
}

val mapPointsModule = module {
    single<MapPointsAddController> { MapPointsAddController() }
    single<MapPointsEditController> { MapPointsEditController() }
    single<MapPointsMenuController> { MapPointsMenuController() }
}

val mapsModule = module {
    single<MapHolderAddController> { MapHolderAddController() }
    single<MapHolderEditController> { MapHolderEditController() }
    single<MapHolderMenuController> { MapHolderMenuController() }
}

val profileRankModule = module {
    single<ProfileRankAddController> { ProfileRankAddController() }
    single<ProfileRankEditController> { ProfileRankEditController() }
    single<ProfileRankMenuController> { ProfileRankMenuController() }
}

val weaponsModule = module {
    single<WeaponsAddController> { WeaponsAddController() }
    single<WeaponsEditController> { WeaponsEditController() }
    single<WeaponsMenuController> { WeaponsMenuController() }
}

val wingmanModule = module {
    single<WingmanAddController> { WingmanAddController() }
    single<WingmanEditController> { WingmanEditController() }
    single<WingmanMenuController> { WingmanMenuController() }
}