package providers.parser_provider

import di.providerModule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.junit5.KoinTestExtension
import providers.ParserItemsGameFileProvider
import utils.isJSON
import java.io.File
import kotlin.test.assertTrue

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class ParserItemsGameFileProviderImplTest : KoinTest {

    companion object {
        private val parserItemsGameFileProvider by inject(ParserItemsGameFileProvider::class.java)
    }

    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {
        modules(providerModule)
    }

    @Test
    fun saveAsFile() {
        val path = "D://123.json"
        parserItemsGameFileProvider.saveAsFile(path)
        assertTrue {
            File(path).exists()
        }
        val txtOfFile = File(path).readText()
        assertTrue(
            txtOfFile.isJSON()
        )
    }

    @Test
    fun getMapOfWeaponRaw() {
        assertTrue(
            parserItemsGameFileProvider.getMapOfWeaponRaw().isNotEmpty()
        )
        assertTrue(
            parserItemsGameFileProvider.getMapOfWeaponRaw().containsKey("ak47")
        )
    }
}