package org.vedenemo.dsl

/**
 * Abstract base class representing an element with a lifecycle based on model versions.
 *
 * @property activeSince The [Version] since which the element is considered active.
 * @property deprecatedSince The [Version] since which the element is considered deprecated.
 */
abstract class Versionable {
    abstract val activeSince: Version
    abstract val deprecatedSince: Version
}
