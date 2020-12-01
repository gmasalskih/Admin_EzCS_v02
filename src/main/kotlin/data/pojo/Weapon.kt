package data.pojo

import data.Entity
import data.enums.FirestoreCollections
import data.enums.Team
import data.enums.WeaponTypes

data class Weapon(

    /** ID */
    val id: String = "",

    /** Имя */
    val name: String = "",

    /** Коллекция в фаербейсе */
    val collection: FirestoreCollections = FirestoreCollections.WEAPONS,

    /** Тип оружия */
    val weaponTypes: WeaponTypes = WeaponTypes.UNKNOWN,

    /** Принадлежность команде */
    val teams: List<Team> = listOf(),

    /** Ссылка на лого */
    val logo: String = "",

    /** Ссылка на файл отдачи. (gif формат) */
    val sprayPattern: String = "",

    /** Ссылка на файл компенсация отдачи. (gif формат) */
    val recoilCompensation: String = "",


    /** Бронипробиваемость. по бронированным противникам урон умножается на armorRatio / 2. */
    val armorRatio: Double = 1.0,

    /** Минимальный интервал между выстрелами следующей пули (измеряется в секундах) */
    val cycleTime: Double = 0.15,

    /** Урон */
    val damage: Int = 42,

    /** Стоимость оружия */
    val inGamePrice: Int = 300,

    /** Неточность сидя */
    val inaccuracyCrouch: Double = 0.0,

    /** Неточность сидя доп. */
    val inaccuracyCrouchAlt: Double = 0.0,

    /** Неточность в движении */
    val inaccuracyMove: Double = 0.0,

    /** Неточность в движении доп. */
    val inaccuracyMoveAlt: Double = 0.0,

    /** Неточность стоя */
    val inaccuracyStand: Double = 0.0,

    /** Неточность стоя доп. */
    val inaccuracyStandAlt: Double = 0.0,

    /** Награда за убийство */
    val killAward: Int = 300,

    /** Максимальная скорость играка с оружием */
    val maxPlayerSpeed: Int = 1,

    /** Коэфицент силы пробивать укрытия */
    val penetration: Double = 1.0,

    /** Емкость обоймы */
    val primaryClipSize: Int = -1,

    /** Максимальное кол-во запасных потронов */
    val primaryReserveAmmoMax: Int = 0,

    /** Коэфицент потери уроны каждые 500U (12.7м) */
    val rangeModifier: Double = 0.98,

    /** Величина отдачи по оси X */
    val recoilAngleVariance: Double = 0.0,

    /** Величина отдачи по оси X доп. */
    val recoilAngleVarianceAlt: Double = 0.0,

    /** Количество отдачи */
    val recoilMagnitude: Double = 0.0,

    /** Количество отдачи доп. */
    val recoilMagnitudeAlt: Double = 0.0,

    /** Величина отклонения отдачи по оси Y */
    val recoilMagnitudeVariance: Double = 0.0,

    /** Величина отклонения отдачи по оси Y доп. */
    val recoilMagnitudeVarianceAlt: Double = 0.0,

    /** Окончательное время востановления прицела сидя */
    val recoveryTimeCrouchFinal: Double = 1.0,

    /** Окончательное время востановления прицела стоя */
    val recoveryTimeStandFinal: Double = 1.0,

    /** Дополнительная погрешность, рассчитанная на пулю */
    val spread: Double = 0.0,

    /** Дополнительная погрешность, рассчитанная на пулю доп */
    val spreadAlt: Double = 0.0,

) : Entity {
    fun getContentsPath() = "${collection.name}/$id/"
}