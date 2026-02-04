plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://plugins.gradle.org/m2/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(libs.gradleplugin.android)
//    implementation(libs.gradleplugin.compose)
//    implementation(libs.gradleplugin.composeCompiler)
    implementation(libs.gradleplugin.kotlin)
    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

//    api(libs.kotlin.gradle.plugin)
//    implementation(libs.gradle.plugin.android.build.tools)
//    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

