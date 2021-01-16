package data.entitys.blueprint_weapon

data class BlueprintWeapon (
	val usedByClasses : UsedByClasses = UsedByClasses(),
	val attributes : Attributes = Attributes(),
	val visuals : Visuals = Visuals()
)