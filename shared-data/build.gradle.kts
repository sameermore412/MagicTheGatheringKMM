plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.9.0"
}

kotlin {
    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.1")
                implementation("io.ktor:ktor-client-core:2.3.2")
                implementation("io.ktor:ktor-client-logging:2.3.2")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
            }
        }
        val desktopMain by getting {
            dependsOn(commonMain)
        }
    }
}