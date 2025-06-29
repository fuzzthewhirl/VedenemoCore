package org.vedenemo.dsl

data class Association(
    val azName: String,
    val visName: String,
    val directionality: Directionality,
    val endA: AssociationEnd,
    val endB: AssociationEnd,
    val attributes: List<Attribute> = emptyList(),
    override val activeSince: Version,
    override val deprecatedSince: Version,
) : Versionable() {
    init {
        require(azName.matches(Regex("^[a-zA-Z0-9]+\$"))) {
            "azName must contain only ASCII alphanumeric characters."
        }
    }
}