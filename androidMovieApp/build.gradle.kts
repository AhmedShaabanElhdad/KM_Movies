plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.km_movie_app.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    packagingOptions {
        exclude("META-INF/kotlinx-io.kotlin_module")
        exclude("META-INF/atomicfu.kotlin_module")
        exclude("META-INF/kotlinx-coroutines-io.kotlin_module")
        exclude("META-INF/kotlinx-coroutines-core.kotlin_module")
    }
}

dependencies {


    val coroutineVersion = "1.6.4"
    val sql_delight_version = "1.5.3"
    val ktor_version = "1.6.7"
    val koin = "3.1.6"

    val compose         = "1.2.0"
    val coil            = "1.4.0"
    val activityCompose = "1.5.1"
    val navigation      = "2.5.1"

    val material        = "1.6.1"

    implementation(project(":shared"))
    implementation("com.google.android.material:material:$material")
    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.compose.material:material:$compose")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose")
    implementation("io.coil-kt:coil-compose:$coil")


    implementation("androidx.navigation:navigation-compose:$navigation")


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
    implementation("io.insert-koin:koin-android:$koin")
    implementation("io.insert-koin:koin-core:$koin")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("io.ktor:ktor-client-android:$ktor_version")



    implementation("io.ktor:ktor-utils-jvm:$ktor_version")
    implementation ("io.ktor:ktor-client-logging-jvm:$ktor_version")
//    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-runtime:2.1.0")

}

