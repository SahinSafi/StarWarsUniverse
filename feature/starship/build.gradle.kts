@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.safi.starship"
}