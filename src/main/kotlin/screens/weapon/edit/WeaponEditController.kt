package screens.weapon.edit

import androidx.compose.runtime.*
import data.entitys.Weapon
import data.types.ContentSourceType
import data.types.FileType
import screens.BaseEditController
import screens.ViewState
import utils.fileChooser

class WeaponEditController : BaseEditController<Weapon, WeaponEditItemViewState>() {

    override val defaultItemViewState: WeaponEditItemViewState = WeaponEditItemViewState()

    override var viewState: ViewState<WeaponEditItemViewState> by mutableStateOf(
        ViewState(
            title = "Edit weapon",
            item = defaultItemViewState
        )
    )

    fun onDelete() {
        launchDeletingEntityOnServer()
    }

    fun onSubmit() {
        launchUpdatingEntityOnServer(viewState.item)
    }

    fun onLogoChange() {
        fileChooser("Select logo", FileType.PNG, viewState.item.logo) { newLogo ->
            setItemViewState(
                viewState.item.copy(
                    logo = newLogo
                )
            )
        }
    }

    fun onSprayChange() {
        fileChooser("Select spray", FileType.GIF, viewState.item.spray) { newSpray ->
            setItemViewState(
                viewState.item.copy(
                    spray = newSpray
                )
            )
        }
    }

    fun onRecoilChange() {
        fileChooser("Select recoil", FileType.GIF, viewState.item.logo) { newRecoil ->
            setItemViewState(
                viewState.item.copy(
                    recoil = newRecoil
                )
            )
        }
    }

    override suspend fun setEntity() {
        service.getEntityAsync(documentName, Weapon::class).await().let { weapon ->
            setItemViewState(
                convertEntityToItemViewSate(weapon)
            )
        }
    }

    override fun convertItemViewSateToEntity(itemViewState: WeaponEditItemViewState): Weapon {
        return itemViewState.weapon!!.copy(
            logo = itemViewState.logo.value,
            spray = itemViewState.spray.value,
            recoil = itemViewState.recoil.value
        )
    }

    override fun convertEntityToItemViewSate(entity: Weapon): WeaponEditItemViewState {
        return WeaponEditItemViewState(
            weapon = entity,
            logo = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.logo),
            spray = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.spray),
            recoil = ContentSourceType.ContentStorageOriginal(entity.getDocumentName(), entity.recoil)
        )
    }
}