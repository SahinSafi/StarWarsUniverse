import com.android.build.gradle.LibraryExtension
import com.safi.starwarsuniverse.SdkVersionConfig
import com.safi.starwarsuniverse.configureKotlinAndroid
import com.safi.starwarsuniverse.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = SdkVersionConfig.TARGET_SDK_VERSION
            }
            configurations.configureEach {
                resolutionStrategy {
                    force(libs.findLibrary("junit").get())
                    force("org.objenesis:objenesis:2.6")
                }
            }
            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
            }
        }
    }
}