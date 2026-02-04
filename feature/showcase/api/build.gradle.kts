plugins {
    id("feature-api-convention")
    alias(libs.plugins.kotlinx.serialization)
}

androidConfig {
    dependencies {
        implementation(libs.kotlinx.serialization)

        api(project(":feature:common"))
    }
}