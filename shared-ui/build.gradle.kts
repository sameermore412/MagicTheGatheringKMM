plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version composeVersion
}

kotlin {
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
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(compose.material)
                implementation("media.kamel:kamel-image:0.7.0")
                implementation("io.ktor:ktor-client-java:2.3.2")
            }
        }
    }
}