plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.navigation)
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
}