plugins {
    id("feature-impl-convention")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)

}

androidConfig {
    dependencies {
        implementation(project(":feature:appDetail:api"))
        implementation(project(":data:apps"))
        implementation(project(":feature:common"))
        implementation(project(":uikit"))

        implementation(libs.coil.compose)
        implementation(libs.coil.network.okhttp)
    }
}