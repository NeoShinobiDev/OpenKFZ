plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.openkfz.app"

    compileSdk = 36

    defaultConfig {
        applicationId = "com.openkfz.app"

        minSdk = 28
        targetSdk = 36

        versionCode = 2
        versionName = "0.1.3"
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
    implementation("androidx.compose.material:material-icons-extended")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.google.zxing:core:3.5.3")

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

    implementation(
        "androidx.compose.material:material-icons-extended"
    )

    implementation(
        "androidx.camera:camera-camera2:1.4.2"
    )
  
    implementation( 
        "androidx.camera:camera-lifecycle:1.4.2"
    )
   
    implementation(
        "androidx.camera:camera-view:1.4.2"
    )

    implementation(
        "androidx.room:room-runtime:2.8.4"
    )

    implementation(
        "androidx.room:room-ktx:2.8.4"
    )

    ksp(
        "androidx.room:room-compiler:2.8.4"
    )

}
