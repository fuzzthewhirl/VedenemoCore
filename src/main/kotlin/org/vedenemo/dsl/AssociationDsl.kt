package org.vedenemo.dsl

fun association(
    azName: String,
    visName: String,
    activeSince: Version,
    deprecatedSince: Version,
    init: AssociationBuilder.() -> Unit
): Association {
    val builder = AssociationBuilder(azName, visName, activeSince, deprecatedSince)
    builder.init()
    return builder.build()
}

class AssociationBuilder(
    private val azName: String,
    private val visName: String,
    private val activeSince: Version,
    private val deprecatedSince: Version
) {
    private var directionality: Directionality = Directionality.UNIDIRECTIONAL
    private var endA: AssociationEnd? = null
    private var endB: AssociationEnd? = null
    private val attributes = mutableListOf<Attribute>()

    fun directionality(dir: Directionality): AssociationBuilder {
        this.directionality = dir
        return this
    }

    fun endA(init: AssociationEndBuilder.() -> Unit): AssociationBuilder {
        this.endA = AssociationEndBuilder().apply(init).build()
        return this
    }

    fun endB(init: AssociationEndBuilder.() -> Unit): AssociationBuilder {
        this.endB = AssociationEndBuilder().apply(init).build()
        return this
    }

    fun attribute(attr: Attribute): AssociationBuilder {
        attributes += attr
        return this
    }

    fun build(): Association {
        requireNotNull(endA) { "endA must be defined" }
        requireNotNull(endB) { "endB must be defined" }

        return Association(
            azName = azName,
            visName = visName,
            directionality = directionality,
            endA = endA!!,
            endB = endB!!,
            attributes = attributes.toList(),
            activeSince = activeSince,
            deprecatedSince = deprecatedSince
        )
    }
}

class AssociationEndBuilder {
    private var entityName: String = ""
    private var role: String = ""
    private var ownership: OwnershipKind = OwnershipKind.REFERENCE

    fun entity(name: String): AssociationEndBuilder {
        this.entityName = name
        return this
    }

    fun role(name: String): AssociationEndBuilder {
        this.role = name
        return this
    }

    fun ownership(kind: OwnershipKind): AssociationEndBuilder {
        this.ownership = kind
        return this
    }

    fun build(): AssociationEnd {
        return AssociationEnd(entityName, role, ownership)
    }
}
