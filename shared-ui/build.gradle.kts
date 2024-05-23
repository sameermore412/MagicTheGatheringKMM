plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version composeVersion
    kotlin("plugin.compose")
    kotlin("native.cocoapods") // Cocoapods plugin
    id("com.android.library")
}

kotlin {
    androidTarget() {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }
    jvm("desktop") {
//        compilations.all {
//            kotlinOptions {
//                jvmTarget = JavaVersion.VERSION_17.toString()
//            }
//            compilerOptions.configure {
//                jvmToolchain(JavaVersion.VERSION_17.ordinal)
//            }
//        }
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
        val voyagerVersion = "1.0.0-rc05"
        val commonMain by getting {
            dependencies {
                implementation(project(":shared-data"))
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation("media.kamel:kamel-image:0.7.0")
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-android:2.3.2")
            }
        }

        val desktopMain by getting {
            dependencies {
                dependsOn(commonMain)
                implementation(compose.desktop.currentOs)
                implementation("media.kamel:kamel-image:0.7.0")
                implementation("io.ktor:ktor-client-java:2.3.2")
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