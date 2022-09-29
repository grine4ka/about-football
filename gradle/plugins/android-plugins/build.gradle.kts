plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation(project(":base-plugins"))
    implementation("com.android.tools.build:gradle")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("de.mannodermaus.gradle.plugins:android-junit5")
}
