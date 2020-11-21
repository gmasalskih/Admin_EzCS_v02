package data.pojo

import data.enums.FirestoreCollections
import data.enums.Team
import data.enums.WeaponTypes

data class Weapon(

    /**Бронипробиваемость. по бронированным противникам урон умножается на armorRatio / 2.*/
    val armorRatio: Double,

    val collection: FirestoreCollections = FirestoreCollections.WEAPONS,

    val contentsPath: String,

    /**Минимальный интервал между выстрелами следующей пули (измеряется в секундах)*/
    val cycleTime: Double,

    /**Урон*/
    val damage: Int,

    val id: String,

    /**Стоимость оружия*/
    val inGamePrice: Int,

    /**Неточность сидя*/
    val inaccuracyCrouch: Double,

    /**Неточность сидя доп.*/
    val inaccuracyCrouchAlt: Double,

    /**Неточность в движении*/
    val inaccuracyMove: Double,

    /**Неточность в движении доп.*/
    val inaccuracyMoveAlt: Double,

    /**Неточность стоя*/
    val inaccuracyStand: Double,

    /**Неточность стоя доп.*/
    val inaccuracyStandAlt: Double,

    /**Награда за убийство*/
    val killAward: Int,

    val logo: String,

    /**Максимальная скорость играка с оружием*/
    val maxPlayerSpeed: Int,

    val name: String,

    /**Коэфицент силы пробивать укрытия*/
    val penetration: Double,

    /**Емкость обоймы*/
    val primaryClipSize: Int,

    /**Максимальное кол-во запасных потронов*/
    val primaryReserveAmmoMax: Int,

    /**Коэфицент потери уроны каждые 500U (12.7м)*/
    val rangeModifier: Double,

    /**величина отдачи по оси X*/
    val recoilAngleVariance: Int,

    /**величина отдачи по оси X доп.*/
    val recoilAngleVarianceAlt: Int,

    val recoilCompensation: String,

    /**количество отдачи*/
    val recoilMagnitude: Double,

    /**количество отдачи доп.*/
    val recoilMagnitudeAlt: Double,

    /**величина отклонения отдачи по оси Y*/
    val recoilMagnitudeVariance: Int,

    /**величина отклонения отдачи по оси Y доп.*/
    val recoilMagnitudeVarianceAlt: Int,

    /**Окончательное время востановления прицела сидя*/
    val recoveryTimeCrouchFinal: Double,

    /**Окончательное время востановления прицела стоя*/
    val recoveryTimeStandFinal: Double,

    val sprayPattern: String,

    /**дополнительная погрешность, рассчитанная на пулю*/
    val spread: Double,

    /**дополнительная погрешность, рассчитанная на пулю доп*/
    val spreadAlt: Double,

    val teams: List<Team>,

    val weaponTypes: WeaponTypes,
)