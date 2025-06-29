package org.vedenemo.dsl

data class Model(
    val azName: String,
    val visName: String,
    val version: Version,
    val entities: Map<String, Entity>,
    val associations: Map<String, Association>
) {

    init {
        require(azName.matches(Regex("^[a-zA-Z0-9]+\$"))) {
            "azName must contain only ASCII alphanumeric characters."
        }

        // Ensure all entity azNames are unique and match map keys
        require(entities.keys.size == entities.values.map { it.azName }.toSet().size) {
            "Duplicate entity azNames detected"
        }

        entities.forEach { (key, entity) ->
            require(key == entity.azName) {
                "Entity key '$key' does not match entity.azName '${entity.azName}'"
            }
        }

        // Ensure all association azNames are unique and match map keys
        require(associations.keys.size == associations.values.map { it.azName }.toSet().size) {
            "Duplicate association azNames detected"
        }

        associations.forEach { (key, assoc) ->
            require(key == assoc.azName) {
                "Association key '$key' does not match association.azName '${assoc.azName}'"
            }
        }
    }
}
