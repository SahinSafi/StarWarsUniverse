plugins {
    alias(libs.plugins.convention.androidLibrary)
    alias(libs.plugins.convention.androidNavigation)
}

android {
    namespace = "com.safi.designsystem"

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.paging)
}