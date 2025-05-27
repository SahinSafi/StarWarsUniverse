@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.convention.androidLibrary)
    alias(libs.plugins.convention.androidHilt)
}

android {
    namespace = "com.safi.domain"
}

dependencies {

    implementation(projects.core.model.entity)
    implementation(projects.core.common)

}