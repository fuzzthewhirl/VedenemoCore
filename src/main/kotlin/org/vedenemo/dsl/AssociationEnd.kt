package org.vedenemo.dsl

data class AssociationEnd(
    val entityName: String,
    val role: String,
    val ownership: OwnershipKind = OwnershipKind.REFERENCE
)