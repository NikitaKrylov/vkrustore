plugins {
    id("feature-impl-convention")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)

}

androidConfig {
    dependencies {
        implementation(project(":feature:onboarding:api"))
        implementation(project(":feature:common"))
        implementation(project(":uikit"))
    }
}