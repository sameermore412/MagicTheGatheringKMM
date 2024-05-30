import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version composeVersion
    kotlin("plugin.compose")
    kotlin("native.cocoapods") // Cocoapods plugin
    id("com.android.library")
}

kotlin {
    androidTarget() {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
    jvm("desktop") {}
    js {
        browser()
        useEsModules()
    }
    wasmJs {
        browser()
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    //Cocoapods configuration
    cocoapods {
        version = "1.0"
        summary = "Shared Compose UI Components"
        name = "sharedui" //Pod spec name
        ios.deploymentTarget = "15.2"
        homepage = "Link to the Shared Module homepage"

        framework {
            baseName = "SharedComposeUI" //This is the name of the package that will be referenced in swift code
            isStatic = true
        }
    }

    sourceSets {
        val voyagerVersion = "1.1.0-beta01"
        val commonMain by getting {
            dependencies {
                implementation(project(":shared-data"))
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation("media.kamel:kamel-image:1.0.0-beta.4")
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-android:3.0.0-wasm2")
            }
        }

        val desktopMain by getting {
            dependencies {
                dependsOn(commonMain)
                implementation(compose.desktop.currentOs)
                implementation("media.kamel:kamel-image:1.0.0-beta.4")
                implementation("io.ktor:ktor-client-java:3.0.0-wasm2")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
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

android {
    namespace= "com.more.mtg.sharedui"
    defaultConfig {
        compileSdk = 33
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(JavaVersion.VERSION_17.ordinal)
    }
}