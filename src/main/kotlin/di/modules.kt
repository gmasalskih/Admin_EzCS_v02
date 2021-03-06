package di

import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.DbxUserFilesRequests
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import utils.DATABASE_URL
import utils.FULL_PATH_TO_SECRET_KEY
import org.koin.dsl.module
import providers.*
import providers.content_provider.ContentProviderImpl
import providers.data_provider.FirebaseAppProvider
import providers.data_provider.DataProviderImpl
import providers.parser_provider.ParserItemsGameFileProviderImpl
import providers.realtime_database.RealtimeDatabaseProviderImpl
import providers.service_provider.ServiceProviderImpl
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
import screens.map_holder.add.MapHolderAddController
import screens.map_holder.edit.MapHolderEditController
import screens.map_holder.menu.MapHolderMenuView
import screens.profile_rank.add.ProfileRankAddController
import screens.profile_rank.edit.ProfileRankEditController
import screens.profile_rank.menu.ProfileRankMenuController
import screens.test.TestController
import screens.test.TestView
import screens.weapon.add.WeaponAddController
import screens.weapon.edit.WeaponEditController
import screens.weapon.menu.WeaponMenuController
import screens.wingman.add.WingmanAddController
import screens.wingman.edit.WingmanEditController
import screens.wingman.menu.WingmanMenuController
import utils.DROPBOX_TOKEN

val appModule = module {
    single<Router> { Router(entryPoint = NavigationTargets.MapHolderMenu to MapHolderMenuView()) }
//    single<Router> { Router(entryPoint = NavigationTargets.Test to TestView()) }
}

val dependencyForProviderModule = module {
    single<Gson> { Gson() }
    single<DbxUserFilesRequests> {
        DbxClientV2(
            DbxRequestConfig.newBuilder("Admin_EzCS/2.0").build(),
            DROPBOX_TOKEN
        ).files()
    }
    single<FirebaseApp> {
        FirebaseAppProvider(
            fullPathToSecretKey = FULL_PATH_TO_SECRET_KEY,
            databaseUrl = DATABASE_URL
        ).getApp()
    }
    single<Firestore> { FirestoreClient.getFirestore(get()) }
    single<FirebaseDatabase> { FirebaseDatabase.getInstance(get<FirebaseApp>()) }
}

val providerModule = module {
    single<ContentProvider> { ContentProviderImpl(get()) }
    single<DataProvider> { DataProviderImpl(get()) }
    single<RealtimeDatabaseProvider> { RealtimeDatabaseProviderImpl(get()) }
    single<ParserItemsGameFileProvider> { ParserItemsGameFileProviderImpl(get()) }
    single<ServiceProvider> { ServiceProviderImpl(get(), get(), get(), get()) }
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

val mapHolderModule = module {
    single<MapHolderAddController> { MapHolderAddController() }
    single<MapHolderEditController> { MapHolderEditController() }
    single<MapHolderMenuController> { MapHolderMenuController() }
}

val profileRankModule = module {
    single<ProfileRankAddController> { ProfileRankAddController() }
    single<ProfileRankEditController> { ProfileRankEditController() }
    single<ProfileRankMenuController> { ProfileRankMenuController() }
}

val weaponModule = module {
    single<WeaponAddController> { WeaponAddController() }
    single<WeaponEditController> { WeaponEditController() }
    single<WeaponMenuController> { WeaponMenuController() }
}

val wingmanModule = module {
    single<WingmanAddController> { WingmanAddController() }
    single<WingmanEditController> { WingmanEditController() }
    single<WingmanMenuController> { WingmanMenuController() }
}

val testModule = module {
    single<TestController> { TestController() }
}