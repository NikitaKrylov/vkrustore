plugins {
    id("feature-impl-convention")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)

}

androidConfig {
    dependencies {
        implementation(libs.kotlinx.serialization)
        implementation(project(":core:common"))
        implementation(project(":uikit"))
    }
}