package org.vedenemo.dsl

data class AssociationEnd(
    // this is azName referring uniquely inside a model
    val entityName: String,
    val role: String,
    val ownership: OwnershipKind = OwnershipKind.REFERENCE
)