import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.AndroidResources
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.api.dsl.Installation
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.ProductFlavor
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.withType
import org.gradle.plugin.use.PluginDependencySpec
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import kotlin.text.get

private const val DEFAULT_PACKAGE_NAME = "com.example.vkrustore"

private typealias AndroidExtensions = CommonExtension<
        out BuildFeatures,
        out BuildType,
        out DefaultConfig,
        out ProductFlavor,
        out AndroidResources,
        out Installation>

private val Project.androidExtension: AndroidExtensions
    get() = extensions.findByType(BaseAppModuleExtension::class)
        ?: extensions.findByType(LibraryExtension::class)
        ?: error(
            "\"Project.androidExtension\" value may be called only from android application" +
                    " or android library gradle script"
        )


fun Project.androidConfig(
    block: AndroidExtensions.() -> Unit = {},
) = with(androidExtension) {
    namespace = unifiedPkg

    compileSdk {
        version = release(libs.versions.compileSdk.get().toInt())
    }

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    block()
}




internal val Project.unifiedPkg: String
    get() = "$DEFAULT_PACKAGE_NAME${
        path
            .replace(":", ".")
            .replace("-", "_")
    }"
