package providers.data_provider

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream

class FirebaseAppProvider(
    private val fullPathToSecretKey: String,
    private val databaseUrl: String
) {
    private var app: FirebaseApp? = null
    fun getApp(): FirebaseApp {
        if (app == null) {
            val serviceAccount = FileInputStream(fullPathToSecretKey)
            val options: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build()
            app = FirebaseApp.initializeApp(options)
        }
        return app!!
    }
}