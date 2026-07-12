plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.openkfz.app"

    compileSdk = 36

    defaultConfig {
        applicationId = "com.openkfz.app"

        minSdk = 28
        targetSdk = 36

        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

}


dependencies {

    implementation(
        "androidx.activity:activity-compose:1.10.1"
    )

    implementation(
        platform(
            "androidx.compose:compose-bom:2025.04.01"
        )
    )

    implementation(
        "androidx.compose.material3:material3"
    )

}
