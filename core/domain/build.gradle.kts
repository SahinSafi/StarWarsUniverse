@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.hilt)
}

android {
    namespace = "com.safi.domain"
}

dependencies {

    implementation(projects.core.model.entity)
    implementation(projects.core.common)

}