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
//    js {
//        browser()
//        useEsModules()
//    }
//    wasmJs() {
//        browser()
//    }


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
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
            }
        }
        val desktopMain by getting {
            dependsOn(commonMain)
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.ios)
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


//        val jsWasmMain by creating {
//            dependsOn(commonMain)
//        }
//
//        val jsMain by getting {
//            dependsOn(jsWasmMain)
//        }
//
//        val wasmJsMain by getting {
//            dependsOn(jsWasmMain)
//        }
    }
}