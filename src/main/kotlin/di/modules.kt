package di

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.StorageClient
import utils.DATABASE_URL
import utils.FULL_PATH_TO_SECRET_KEY
import org.koin.dsl.module
import providers.dropbox.DropboxProvider
import providers.firebase.FirebaseAppProvider
import providers.firebase.FirestoreProvider
import router.NavigationTargets
import router.Router
import screens.competitive.add.CompetitiveAddController
import screens.competitive.edit.CompetitiveEditController
import screens.competitive.menu.CompetitiveMenuController
import screens.danger_zone.add.DangerZoneAddController
import screens.danger_zone.edit.DangerZoneEditController
import screens.danger_zone.menu.DangerZoneMenuController
import screens.map_point.menu.MapPointMenuController
import screens.map_point.add.MapPointAddController
import screens.map_point.edit.MapPointEditController
import screens.map_holder.menu.MapHolderMenuController
import screens.map_holder.menu.MapHolderMenuView
import screens.map_holder.add.MapHolderAddController
import screens.map_holder.edit.MapHolderEditController
import screens.profile_rank.add.ProfileRankAddController
import screens.profile_rank.edit.ProfileRankEditController
import screens.profile_rank.menu.ProfileRankMenuController
import screens.weapon.add.WeaponAddController
import screens.weapon.edit.WeaponEditController
import screens.weapon.menu.WeaponMenuController
import screens.wingman.add.WingmanAddController
import screens.wingman.edit.WingmanEditController
import screens.wingman.menu.WingmanMenuController

val appModule = module {
    single<Router> { Router(entryPoint = NavigationTargets.MapHolderMenu to MapHolderMenuView()) }
}

val dropboxModule = module {
    single<DropboxProvider> { DropboxProvider() }
}

val fbModules = module {
    single<FirebaseApp> {
        FirebaseAppProvider(
            fullPathToSecretKey = FULL_PATH_TO_SECRET_KEY,
            databaseUrl = DATABASE_URL
        ).getApp()
    }
    single<StorageClient> { StorageClient.getInstance(get()) }
    single<FirestoreProvider> { FirestoreProvider(get()) }
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
    single<MapPointAddController> { MapPointAddController() }
    single<MapPointEditController> { MapPointEditController() }
    single<MapPointMenuController> { MapPointMenuController() }
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
    single<WeaponAddController> { WeaponAddController() }
    single<WeaponEditController> { WeaponEditController() }
    single<WeaponMenuController> { WeaponMenuController() }
}

val wingmanModule = module {
    single<WingmanAddController> { WingmanAddController() }
    single<WingmanEditController> { WingmanEditController() }
    single<WingmanMenuController> { WingmanMenuController() }
}