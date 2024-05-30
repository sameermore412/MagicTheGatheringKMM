import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("native.cocoapods") // Cocoapods plugin
}

kotlin {
    jvm("desktop")
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js {
        browser()
        useEsModules()
    }
    wasmJs() {
        browser()
    }


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
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0-wasm2")
                implementation("io.ktor:ktor-client-core:3.0.0-wasm2")
                implementation("io.ktor:ktor-client-logging:3.0.0-wasm2")
                implementation("io.ktor:ktor-client-content-negotiation:3.0.0-wasm2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0-wasm2")
            }
        }
        val desktopMain by getting {
            dependsOn(commonMain)
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-ios:3.0.0-wasm2")
            }
        }

        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }


        val jsWasmMain by creating {
            dependsOn(commonMain)
        }

        val jsMain by getting {
            dependsOn(jsWasmMain)
        }

        val wasmJsMain by getting {
            dependsOn(jsWasmMain)
        }
    }
}