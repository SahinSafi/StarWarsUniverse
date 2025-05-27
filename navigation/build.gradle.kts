@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.convention.androidLibrary)
    alias(libs.plugins.convention.androidNavigation)
}

android {
    namespace = "com.safi.navigation"
}

dependencies {
    implementation(projects.core.model.entity)
}