package org.vedenemo.dsl

data class Entity(
    val azName: String,
    val visName: String,
    val attributes: List<Attribute> = emptyList(),
    override val activeSince: Version,
    override val deprecatedSince: Version? = null
) : Versionable() {

    init {
        require(azName.matches(Regex("^[a-zA-Z0-9]+\$"))) {
            "azName must contain only ASCII alphanumeric characters."
        }
    }
}
