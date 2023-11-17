plugins {
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.safi.common"
}

dependencies {
    implementation(libs.material)
}