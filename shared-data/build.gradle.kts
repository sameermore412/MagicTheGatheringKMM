plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("native.cocoapods") // Cocoapods plugin
}

kotlin {
    jvm("desktop")
    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        name = "shareddata"
        ios.deploymentTarget = "15.2"
        framework {
            baseName = "SharedData"
            isStatic = true
        }
    }

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

        val iosSimulatorArm64Main by getting

        val iosMain by getting {
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}