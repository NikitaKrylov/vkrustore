plugins {
    id("feature-impl-convention")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)

}

androidConfig {
    dependencies {
        implementation(project(":data:apps"))
        implementation(project(":feature:showcase:api"))
        implementation(project(":feature:common"))
        implementation(project(":uikit"))

        implementation(libs.androidx.work.runtime.ktx)

    }
}