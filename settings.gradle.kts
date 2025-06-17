pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("io.quarkus") version "3.10.0"
        kotlin("jvm") version "1.9.22"  // 🟢 Use this instead of 1.9.10
    }
}
rootProject.name = "VedenemoCore"
