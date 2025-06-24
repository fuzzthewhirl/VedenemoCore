package org.vedenemo.core

/**
 * Represents an attribute within a model, including its name, visibility, lifecycle, and type.
 *
 * @property azName ASCII-compliant name of the attribute, must be unique within the model.
 * @property visName Visual name for the attribute, UTF-8 string. Uniqueness is not enforced.
 * @property type The [DataType] representing the type of this attribute.
 */
data class Attribute(
    val azName: String,
    val visName: String,
    override val activeSince: Version,
    override val deprecatedSince: Version,
    val type: DataType
) : Versionable() {
    init {
        require(azName.matches(Regex("^[a-zA-Z0-9]+\$"))) {
            "azName must contain only ASCII alphanumeric characters."
        }
    }
}

/**
 * Enumeration of possible data types for an attribute.
 */
enum class DataType {
    /** Textual data type */
    TEXT,

    /** Numeric data type */
    NUMERIC,

    /** URL data type */
    URL,

    /** Binary or file-based data type */
    DATA
}
