
plugins {
    id("com.android.library")
    kotlin("android")
}

androidConfig()

dependencies {
    implementation(platform(libs.koin.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.koin.core)
    implementation(libs.koin.android)

    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
    testImplementation(libs.koin.testing.junit4)
}