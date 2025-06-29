package org.vedenemo.dsl

import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class AssociationDslTest {

    companion object {
            val NOW: LocalDateTime

            init {
                NOW = LocalDateTime.now()
            }
    }

    @Test
    fun `test building association with DSL`() {
        val assoc = association(
            azName = "employment",
            visName = "Employment",
            activeSince = Version(VersionType.SNAPSHOT, 1, minor=0, patch=0, since=NOW),
            null
        ) {
            directionality(Directionality.UNIDIRECTIONAL)

            endA {
                entity("Person")
                role("employer")
                ownership(OwnershipKind.CONTAINMENT)
            }

            endB {
                entity("Organization")
                role("employee")
            }

            attribute(
                Attribute(
                    azName = "startDate",
                    visName = "Start Date",
                    type = DataType.TEXT,
                    activeSince = Version(VersionType.SNAPSHOT, 1, minor=0, patch=0, since=NOW)
                )
            )
        }

        assertEquals("employment", assoc.azName)
        assertEquals(Directionality.UNIDIRECTIONAL, assoc.directionality)
        assertEquals("Person", assoc.endA.entityName)
        assertEquals("Organization", assoc.endB.entityName)
        assertEquals(1, assoc.attributes.size)
        assertEquals("startDate", assoc.attributes.first().azName)
        assertEquals(Version(VersionType.SNAPSHOT, 1, minor=0, patch=0, since=NOW), assoc.activeSince)
    }
}
