val ktorVersion: String by project
val serializationVersion: String by project
val coroutineVersion: String by project
val gcpVersion: String by project
val slf4jVersion: String by project
val koinVersion: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
    id("io.ktor.plugin") version "2.3.8"
}

group = "io.github.cotrin1208"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // SLF4J
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    // ktor
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-double-receive:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-auth:$ktorVersion")
    // Koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    // Kotlin Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    // Google Cloud Platform
    implementation(platform("com.google.cloud:libraries-bom:$gcpVersion"))
    implementation("com.google.cloud:google-cloud-datastore")
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.8")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.22")
}

application {
    mainClass.set("io.github.cotrin1208.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
