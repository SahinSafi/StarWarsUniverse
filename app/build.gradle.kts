plugins {
    alias(libs.plugins.convention.android.application)
    alias(libs.plugins.convention.android.navigation)
    alias(libs.plugins.convention.android.hilt)
}

android {
    namespace = "com.safi.starwarsuniverse"

    defaultConfig {
        applicationId = "com.safi.starwarsuniverse"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        debug {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.designSystem)
    implementation(projects.navigation)


    implementation(projects.feature.character)
    implementation(projects.feature.starship)
    implementation(projects.feature.planet)


    implementation(libs.androidx.corektx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}