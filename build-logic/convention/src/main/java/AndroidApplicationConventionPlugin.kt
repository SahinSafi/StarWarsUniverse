import com.android.build.api.dsl.ApplicationExtension
import com.safi.starwarsuniverse.SdkVersionConfig
import com.safi.starwarsuniverse.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                //apply("nowinandroid.android.lint")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = SdkVersionConfig.TARGET_SDK_VERSION
            }
        }
    }

}