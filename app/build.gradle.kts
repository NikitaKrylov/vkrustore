plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.weater.vkrustore"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.weater.vkrustore"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(project(":core:common"))
    implementation(project(":core:db"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:storage"))

    implementation(project(":feature:account:api"))
    implementation(project(":feature:account:impl"))

    implementation(project(":feature:appDetail:api"))
    implementation(project(":feature:appDetail:impl"))

    implementation(project(":feature:categories:api"))
    implementation(project(":feature:categories:impl"))

    implementation(project(":feature:onboarding:api"))
    implementation(project(":feature:onboarding:impl"))

    implementation(project(":feature:search:api"))
    implementation(project(":feature:search:impl"))

    implementation(project(":feature:showcase:api"))
    implementation(project(":feature:showcase:impl"))


}