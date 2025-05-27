@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.convention.androidLibrary)
    alias(libs.plugins.convention.androidHilt)
}

android {
    namespace = "com.safi.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.model.response)
    implementation(projects.core.model.entity)
    implementation(libs.bundles.network)
    implementation(libs.log.timber)
}