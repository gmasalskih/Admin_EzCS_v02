package data.entitys.blueprint_weapon

data class Attributes(
    val armorRatio: Double = 1.0,
    val cycletime: Double = 0.15,
    val damage: Int = 42,
    val inGamePrice: Int = 700,
    val inaccuracyCrouch: Double = 0.0,
    val inaccuracyCrouchAlt: Double = 0.0,
    val inaccuracyMove: Double = 0.0,
    val inaccuracyMoveAlt: Double = 0.0,
    val inaccuracyStand: Double = 0.0,
    val inaccuracyStandAlt: Double = 0.0,
    val killAward: Int = 300,
    val maxPlayerSpeed: Int = 1,
    val penetration: Double = 1.0,
    val primaryClipSize: Int = -1,
    val primaryReserveAmmoMax: Int = 0,
    val rangeModifier: Double = 0.98,
    val recoilAngleVariance: Double = 0.0,
    val recoilAngleVarianceAlt: Double = 0.0,
    val recoilMagnitude: Double = 0.0,
    val recoilMagnitudeAlt: Double = 0.0,
    val recoilMagnitudeVariance: Double = 0.0,
    val recoilMagnitudeVarianceAlt: Double = 0.0,
    val recoveryTimeCrouchFinal: Double = 1.0,
    val recoveryTimeStandFinal: Double = 1.0,
    val spread: Double = 0.0,
    val spreadAlt: Double = 0.0,
)