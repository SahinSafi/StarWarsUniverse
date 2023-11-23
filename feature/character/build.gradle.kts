@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.convention.feature)
}

android {
    namespace = "com.safi.character"
}

dependencies {
    implementation(libs.androidx.paging)
    implementation(libs.image.circle)
}