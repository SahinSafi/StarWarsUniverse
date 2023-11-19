@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
}

android {
    namespace = "com.safi.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.bundles.network)
    implementation(libs.log.timber)
}