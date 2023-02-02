pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KM_Movie_App"
//enableFeaturePreview("GRADLE_METADATA")
include(":androidMovieApp")
include(":shared")