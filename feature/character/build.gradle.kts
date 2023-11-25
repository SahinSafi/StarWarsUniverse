@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.convention.feature)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.safi.character"
}

dependencies {
    implementation(libs.androidx.paging)
    implementation(libs.image.circle)
}