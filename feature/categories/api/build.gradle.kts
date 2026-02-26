plugins {
    id("feature-api-convention")
    alias(libs.plugins.kotlinx.serialization)
}

androidConfig {
    dependencies {
        implementation(libs.kotlinx.serialization)
        implementation(project(":core:common"))
        api(project(":feature:common"))
    }
}