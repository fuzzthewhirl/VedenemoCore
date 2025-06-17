plugins {
    kotlin("jvm") version "1.9.10"
    id("io.quarkus")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:3.10.0"))
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-websockets")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    testImplementation("io.quarkus:quarkus-junit5")
}
