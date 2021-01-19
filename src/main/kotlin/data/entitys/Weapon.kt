package data.entitys

import data.types.EntityType
import data.types.TeamType
import data.types.WeaponType
import utils.ContentType
import utils.DataType
import utils.toValidId

data class Weapon(

    @DataType
    override val name: String = "",

    @DataType
    override val entityType: EntityType = EntityType.WEAPON,

    @DataType
    val externalId:String = "",

    @DataType /** Тип оружия */
    val weaponType: WeaponType = WeaponType.PISTOL,

    @DataType /** Принадлежность команде */
    val teamTypes: List<TeamType> = listOf(),

    @ContentType /** Ссылка на лого */
    val logo: String = "",

    @ContentType /** Ссылка на файл отдачи. (gif формат) */
    val spray: String = "",

    @ContentType /** Ссылка на файл компенсация отдачи. (gif формат) */
    val recoil: String = "",

    @DataType /** Бронипробиваемость. по бронированным противникам урон умножается на armorRatio / 2. */
    val armorRatio: Double = 1.0,

    @DataType /** Минимальный интервал между выстрелами следующей пули (измеряется в секундах) */
    val cycleTime: Double = 0.15,

    @DataType /** Урон */
    val damage: Int = 42,

    @DataType /** Стоимость оружия */
    val inGamePrice: Int = 300,

    @DataType /** Неточность сидя */
    val inaccuracyCrouch: Double = 0.0,

    @DataType /** Неточность сидя доп. */
    val inaccuracyCrouchAlt: Double = 0.0,

    @DataType /** Неточность в движении */
    val inaccuracyMove: Double = 0.0,

    @DataType /** Неточность в движении доп. */
    val inaccuracyMoveAlt: Double = 0.0,

    @DataType /** Неточность стоя */
    val inaccuracyStand: Double = 0.0,

    @DataType /** Неточность стоя доп. */
    val inaccuracyStandAlt: Double = 0.0,

    @DataType /** Награда за убийство */
    val killAward: Int = 300,

    @DataType /** Максимальная скорость играка с оружием */
    val maxPlayerSpeed: Int = 1,

    @DataType /** Коэфицент силы пробивать укрытия */
    val penetration: Double = 1.0,

    @DataType /** Емкость обоймы */
    val primaryClipSize: Int = -1,

    @DataType /** Максимальное кол-во запасных потронов */
    val primaryReserveAmmoMax: Int = 0,

    @DataType /** Коэфицент потери уроны каждые 500U (12.7м) */
    val rangeModifier: Double = 0.98,

    @DataType /** Величина отдачи по оси X */
    val recoilAngleVariance: Double = 0.0,

    @DataType /** Величина отдачи по оси X доп. */
    val recoilAngleVarianceAlt: Double = 0.0,

    @DataType /** Количество отдачи */
    val recoilMagnitude: Double = 0.0,

    @DataType /** Количество отдачи доп. */
    val recoilMagnitudeAlt: Double = 0.0,

    @DataType /** Величина отклонения отдачи по оси Y */
    val recoilMagnitudeVariance: Double = 0.0,

    @DataType /** Величина отклонения отдачи по оси Y доп. */
    val recoilMagnitudeVarianceAlt: Double = 0.0,

    @DataType /** Окончательное время востановления прицела сидя */
    val recoveryTimeCrouchFinal: Double = 1.0,

    @DataType /** Окончательное время востановления прицела стоя */
    val recoveryTimeStandFinal: Double = 1.0,

    @DataType /** Дополнительная погрешность, рассчитанная на пулю */
    val spread: Double = 0.0,

    @DataType /** Дополнительная погрешность, рассчитанная на пулю доп */
    val spreadAlt: Double = 0.0,
) : Entity{
    override fun getDocumentName(): String {
        return "${entityType.name}/${externalId.toValidId()}"
    }
}