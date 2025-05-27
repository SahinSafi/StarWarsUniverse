import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.safi.starwarsuniverse.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.convention.androidApplication.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.convention.androidLibrary.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidNavigation") {
            id = libs.plugins.convention.androidNavigation.get().pluginId
            implementationClass = "AndroidNavigationConventionPlugin"
        }

        register("androidHilt") {
            id = libs.plugins.convention.androidHilt.get().pluginId
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("conventionFeature") {
            id = libs.plugins.convention.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}
