plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")


}

version = "1.0"


kotlin {
    android()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosMovieApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {

        val coroutineVersion = "1.6.4"
        val sql_delight_version = "1.5.3"
        val ktor_version = "1.6.7"
        val koin = "3.1.6"

        // region common
        val commonMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:runtime:$sql_delight_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")

                implementation("io.insert-koin:koin-core:$koin")
                implementation("io.insert-koin:koin-test:$koin")
                implementation("io.insert-koin:koin-android:$koin")

                //Logger
                implementation("com.github.aakira:napier:1.4.1")
//                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$ktor_version")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
                implementation("com.squareup.sqldelight:android-driver:$sql_delight_version")
                implementation("io.ktor:ktor-client-android:$ktor_version")
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
//                implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
//                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$ktor_version")

            }
        }
        val androidTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation("com.squareup.sqldelight:sqlite-driver:$sql_delight_version")
            }
        }




        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktor_version")
                implementation("com.squareup.sqldelight:native-driver:$sql_delight_version")
                implementation("io.ktor:ktor-client-ios:$ktor_version")

            }
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

    }

}

// workaround for https://youtrack.jetbrains.com/issue/KT-27170
//configurations {
//    compileClasspath
//}

//task packForXCode(type : Sync) {
//    final File frameworkDir = new File(buildDir, "xcode-frameworks")
//    final String mode = project.findProperty("XCODE_CONFIGURATION")?.toUpperCase() ?: 'DEBUG'
//
//    inputs.property "mode", mode
//    dependsOn kotlin.targets.ios.compilations.main.linkTaskName("FRAMEWORK", mode)
//
//    from { kotlin.targets.ios.compilations.main.getBinary("FRAMEWORK", mode).parentFile }
//    into frameworkDir
//
//            doLast {
//                new File(frameworkDir, 'gradlew').with {
//                text = "#!/bin/bash\nexport 'JAVA_HOME=${System.getProperty("java.home")}'\ncd '${rootProject.rootDir}'\n./gradlew \$@\n"
//                setExecutable(true)
//            }
//            }
//}
//
//tasks.build.dependsOn packForXCode

sqldelight {
    database("MoviesDb") {
        packageName = "com.example.km_movie_app.cache"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    compileSdk = 32
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }


    lint {
        checkAllWarnings = true
        abortOnError = true
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}
dependencies {
    testImplementation("junit:junit:4.12")
}
