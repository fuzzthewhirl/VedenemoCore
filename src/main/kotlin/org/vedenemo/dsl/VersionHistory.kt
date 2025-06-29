package org.vedenemo.dsl

/**
 * Maintains a history of software versions in chronological order.
 *
 * @property versions An ordered list of versions from oldest to newest.
 */
class VersionHistory(
    versions: List<Version>
) {
    private val _versions: MutableList<Version> = versions
        .distinctBy { Triple(it.major, it.minor, it.patch) }
        .sortedWith(compareBy({ it.major }, { it.minor }, { it.patch }))
        .toMutableList()

    /**
     * Returns the list of versions in chronological order.
     */
    val versions: List<Version> get() = _versions

    /**
     * Returns the latest (newest) version in the history.
     *
     * @return The most recent [Version] in the list.
     */
    fun getLatestVersion(): Version = _versions.last()

    /**
     * Checks whether a specific version exists in the history.
     *
     * @param version The version to check for.
     * @return True if the version exists, false otherwise.
     */
    fun existsVersion(version: Version): Boolean =
        _versions.any { it.isEqual(version) }

    /**
     * Adds a new version to the version history, maintaining chronological order.
     * If a version with the same major, minor, and patch exists, it will be replaced.
     *
     * @param version The [Version] to be added or replaced.
     */
    fun add(version: Version) {
        val existingIndex = _versions.indexOfFirst { it.isEqual(version) }
        if (existingIndex != -1) {
            _versions.removeAt(existingIndex)
        }

        val index = _versions.indexOfFirst { version.isOlder(it) }
        if (index == -1) {
            _versions.add(version)
        } else {
            _versions.add(index, version)
        }
    }
}
