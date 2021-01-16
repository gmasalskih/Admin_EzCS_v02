package providers.realtime_database

import data.entitys.blueprint_weapon.BlueprintWeapon
import di.providerModule
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.junit5.KoinTestExtension
import providers.ParserItemsGameFileProvider
import providers.RealtimeDatabaseProvider

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class RealtimeDatabaseProviderImplTest: KoinTest {

    companion object {
        private val realtimeDatabaseProvider by inject(RealtimeDatabaseProvider::class.java)
        private val parserItemsGameFileProvider by inject(ParserItemsGameFileProvider::class.java)
    }

    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {
        modules(providerModule)
    }

    @Test
    @Order(1)
    fun `clear db`() = runBlocking {
        assertTrue(realtimeDatabaseProvider.clear())
    }

    @Test
    @Order(2)
    fun `save document`() = runBlocking {
        assertTrue(
            realtimeDatabaseProvider.saveDocument(parserItemsGameFileProvider.getMapOfWeaponRaw())
        )
    }

    @Test
    fun `get document`() = runBlocking {
        val res = realtimeDatabaseProvider.getDocument("ak47", BlueprintWeapon::class.java)
        assertEquals(res.usedByClasses.terrorists, 1, "terrorists")
        assertEquals(res.usedByClasses.counterTerrorists, 0, "counterTerrorists")
    }

    @Test
    fun `get documents`() = runBlocking {
        assertTrue(
            realtimeDatabaseProvider.getDocuments().isNotEmpty()
        )
        assertTrue(
            realtimeDatabaseProvider.getDocuments().contains("ak47")
        )
    }
}