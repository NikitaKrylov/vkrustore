plugins {
    id("common-convention")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)
}

androidConfig {
    dependencies {
        implementation(libs.androidx.navigation.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.kotlinx.serialization)
        implementation(project(":core:common"))
    }
}