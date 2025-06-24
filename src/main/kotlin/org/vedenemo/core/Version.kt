package org.vedenemo.core
import java.time.LocalDateTime

/**
 * Represents a software version with semantic versioning and a type indicator.
 *
 * @property type The type of the version, either [VersionType.SNAPSHOT] or [VersionType.RELEASE].
 * @property major The major version number. Must be a positive integer.
 * @property minor The minor version number. Must be a positive integer.
 * @property patch The patch version number. Must be a positive integer.
 * @property since The date and time when this version became effective.
 */
data class Version(
    val type: VersionType,

    /**
     * The major version number. Incremented for incompatible API changes.
     */
    val major: Int,

    /**
     * The minor version number. Incremented for added functionality in a backward-compatible manner.
     */
    val minor: Int,

    /**
     * The patch version number. Incremented for backward-compatible bug fixes.
     */
    val patch: Int,

    /**
     * The date and time when this version was introduced.
     */
    val since: LocalDateTime
) {
    init {
        require(major > 0) { "Major version must be a positive integer." }
        require(minor >= 0) { "Minor version must be zero or a positive integer." }
        require(patch >= 0) { "Patch version must be zero or a positive integer." }
    }

    /**
     * Checks if this version is equal to another version (ignoring type).
     */
    fun isEqual(other: Version): Boolean =
        this.major == other.major &&
                this.minor == other.minor &&
                this.patch == other.patch

    /**
     * Checks if this version is older than another version (ignoring type).
     */
    fun isOlder(other: Version): Boolean =
        when {
            this.major != other.major -> this.major < other.major
            this.minor != other.minor -> this.minor < other.minor
            else -> this.patch < other.patch
        }

    /**
     * Checks if this version is newer than another version (ignoring type).
     */
    fun isNewer(other: Version): Boolean =
        when {
            this.major != other.major -> this.major > other.major
            this.minor != other.minor -> this.minor > other.minor
            else -> this.patch > other.patch
        }
}

/**
 * Enumeration of version types.
 */
enum class VersionType {
    /**
     * A development version that is not yet considered stable.
     */
    SNAPSHOT,

    /**
     * A stable, released version.
     */
    RELEASE
}
