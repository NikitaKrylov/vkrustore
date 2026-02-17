
plugins {
    id("common-convention")
    alias(libs.plugins.kotlinx.serialization)
}

androidConfig {
    dependencies {
        implementation(libs.kotlinx.serialization)
        implementation(libs.androidx.datastore.preferences)
    }
}