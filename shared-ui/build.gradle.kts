plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version composeVersion
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
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
            compilerOptions.configure {
                jvmToolchain(JavaVersion.VERSION_17.ordinal)
            }
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared-data"))
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation("media.kamel:kamel-image:0.7.0")
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

    composeOptions {
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }
}